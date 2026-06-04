package com.ll.blog.service.impl;

import com.ll.blog.cache.ArticleCache;
import com.ll.blog.exception.ArticleNotExistException;
import com.ll.blog.model.dto.ArticlesPageQueryDTO;
import com.ll.blog.model.vo.ArticleDetailVO;
import com.ll.blog.model.vo.ArticleLinkVO;
import com.ll.blog.model.vo.ArticlePageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.service.ArticleService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.ll.blog.utils.MarkdownParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HexoArticleServiceImpl implements ArticleService {
    private final ArticleCache articleCache;

    private static final Pattern WIKI_LINK_PATTERN = Pattern.compile("\\[\\[([^]|]+?)]]");
    private static final int MAX_PAGE_SIZE = 20;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public PageResult<ArticlePageQueryVO> page(ArticlesPageQueryDTO dto) {
        int page = dto.getPage() != null ? dto.getPage() : 1;
        int size = dto.getSize() != null ? dto.getSize() : 5;
        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        }

        // 过滤：按标签和关键词
        List<MarkdownParser.ArticleMeta> filtered = articleCache.filter(dto.getTag(), dto.getKeyword());
        int total = filtered.size();
        int from = (page - 1) * size;
        int to = Math.min(from + size, total);
        if (from >= total) {
            return new PageResult<>((long) total, Collections.emptyList());
        }
        List<MarkdownParser.ArticleMeta> pageItems = filtered.subList(from, to);

        List<ArticlePageQueryVO> vos = pageItems.stream()
                .map(this::toArticlePageQueryVO)
                .collect(Collectors.toList());
        return new PageResult<>((long) total, vos);
    }

    @Override
    public ArticleLinkVO getArticleLink() {
        List<MarkdownParser.ArticleMeta> allArticles = articleCache.getSortedList();
        if (allArticles.isEmpty()) {
            return new ArticleLinkVO(Collections.emptyList(), Collections.emptyList());
        }

        // 构建 slug → 文章映射
        Map<String, MarkdownParser.ArticleMeta> slugMap = allArticles.stream()
                .collect(Collectors.toMap(MarkdownParser.ArticleMeta::slug, a -> a, (a, b) -> a));

        // 构建节点（因为没有分类，group 可以传空或 "未分类"）
        List<ArticleLinkVO.Node> nodes = allArticles.stream()
                .map(meta -> new ArticleLinkVO.Node(meta.slug(), meta.title(), "未分类"))
                .collect(Collectors.toList());

        // 构建连线（去重）
        Set<String> linkSet = new HashSet<>();
        List<ArticleLinkVO.Link> links = new ArrayList<>();
        for (MarkdownParser.ArticleMeta article : allArticles) {
            String content = article.content();
            if (StrUtil.isBlank(content)) continue;
            Matcher matcher = WIKI_LINK_PATTERN.matcher(content);
            while (matcher.find()) {
                String targetSlug = matcher.group(1).trim();
                if (!targetSlug.equals(article.slug()) && slugMap.containsKey(targetSlug)) {
                    String key = article.slug() + "→" + targetSlug;
                    if (!linkSet.contains(key)) {
                        linkSet.add(key);
                        links.add(new ArticleLinkVO.Link(article.slug(), targetSlug));
                    }
                }
            }
        }
        return new ArticleLinkVO(nodes, links);
    }

    @Override
    public ArticleDetailVO getArticleSlug(String slug) {
        MarkdownParser.ArticleMeta meta = articleCache.getBySlug(slug);
        if (meta == null) {
            throw new ArticleNotExistException("文章不存在", 404);
        }

        ArticleDetailVO vo = new ArticleDetailVO();
        vo.setTitle(meta.title());
        vo.setSlug(meta.slug());
        vo.setContent(meta.content());
        vo.setTags(meta.tags());
        vo.setCreatedAt(parseDateTime(meta.date()));
        vo.setUpdatedAt(null);  // Markdown 无更新日期

        // 前一篇 & 后一篇
        List<MarkdownParser.ArticleMeta> sorted = articleCache.getSortedList();
        int idx = sorted.indexOf(meta);
        if (idx > 0) {
            MarkdownParser.ArticleMeta prev = sorted.get(idx - 1);
            vo.setPrevArticle(new ArticleDetailVO.ArticleLink(prev.slug(), prev.title()));
        }
        if (idx >= 0 && idx < sorted.size() - 1) {
            MarkdownParser.ArticleMeta next = sorted.get(idx + 1);
            vo.setNextArticle(new ArticleDetailVO.ArticleLink(next.slug(), next.title()));
        }

        return vo;
    }

    // ---- 辅助方法 ----

    private ArticlePageQueryVO toArticlePageQueryVO(MarkdownParser.ArticleMeta meta) {
        ArticlePageQueryVO vo = BeanUtil.copyProperties(meta, ArticlePageQueryVO.class);
        vo.setId(0L);                     // Markdown 文章无自增 ID
        vo.setCreatedAt(parseDateTime(meta.date()));
        vo.setUpdatedAt(null);
        return vo;
    }

    private LocalDateTime parseDateTime(String dateStr) {
        if (StrUtil.isBlank(dateStr)) return null;
        try {
            return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
            } catch (DateTimeParseException ex) {
                return null;
            }
        }
    }
}
