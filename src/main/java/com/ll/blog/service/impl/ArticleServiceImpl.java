package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ll.blog.exception.BusinessException;
import com.ll.blog.mapper.ArticleMapper;
import com.ll.blog.mapper.ArticleTagMapper;
import com.ll.blog.mapper.CategoryMapper;
import com.ll.blog.mapper.TagMapper;
import com.ll.blog.model.dto.ArticlesPageQueryDTO;
import com.ll.blog.model.po.Article;
import com.ll.blog.model.po.ArticleTag;
import com.ll.blog.model.po.Category;
import com.ll.blog.model.vo.ArticleDetailVO;
import com.ll.blog.model.vo.ArticleLinkVO;
import com.ll.blog.model.vo.ArticlePageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.ll.blog.content.PageQuery.MAX_PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final CategoryMapper categoryMapper;
    private final ArticleTagMapper articleTagMapper;  // 新增
    private final TagMapper tagMapper;                // 新增
    // 匹配 [[...]] 双链，捕获组为 slug（不包含管道符别名）
    private static final Pattern WIKI_LINK_PATTERN = Pattern.compile("\\[\\[([^]|]+?)]]");


    @Override
    public PageResult<ArticlePageQueryVO> page(ArticlesPageQueryDTO dto) {
        int page = dto.getPage() != null ? dto.getPage() : 1;
        int size = dto.getSize() != null ? dto.getSize() : 5;
        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        }
        Page<Article> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<Article>()
                .orderByDesc(true, Article::getCreatedAt);
        Page<Article> articlePage = articleMapper.selectPage(pageParam, wrapper);
        List<ArticlePageQueryVO> vos = BeanUtil.copyToList(articlePage.getRecords(), ArticlePageQueryVO.class);
        return new PageResult<>(articlePage.getTotal(), vos);
    }

    @Override
    public ArticleLinkVO getArticleLink() {
        /*// 1. 查询所有已发布文章（只需要 slug, title, content, category_id）
        List<Article> articles = articleMapper.selectList(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getIsPublished, 1)
                        .select(Article::getSlug, Article::getTitle, Article::getContent, Article::getCategoryId)
        );

        if (articles.isEmpty()) {
            return new ArticleLinkVO(Collections.emptyList(), Collections.emptyList());
        }

        // 2. 建立 slug → article 映射
        Map<String, Article> slugMap = articles.stream()
                .collect(Collectors.toMap(Article::getSlug, a -> a, (a, b) -> a));

        // 3. 构建节点列表
        List<ArticleLinkVO.Node> nodes = articles.stream()
                .map(article -> {
                    String group = getCategoryNameOrDefault(article.getCategoryId());
                    return new ArticleLinkVO.Node(article.getSlug(), article.getTitle(), group);
                })
                .collect(Collectors.toList());*/
        // 1. 查询所有已发布文章
        List<Article> articles = articleMapper.selectList(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getIsPublished, 1)
                        .select(Article::getSlug, Article::getTitle, Article::getContent, Article::getCategoryId)
        );

        if (articles.isEmpty()) {
            return new ArticleLinkVO(Collections.emptyList(), Collections.emptyList());
        }

        // 2. 收集所有 categoryId（用于批量查分类）
        Set<Long> categoryIds = articles.stream()
                .map(Article::getCategoryId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // 3. 批量查询分类名，建立 id -> name 映射
        Map<Long, String> categoryNameMap = new HashMap<>();
        if (!categoryIds.isEmpty()) {
            List<Category> categories = categoryMapper.selectList(
                    new LambdaQueryWrapper<Category>().in(Category::getId, categoryIds)
            );
            categories.forEach(c -> categoryNameMap.put(c.getId(), c.getName()));
        }

        // 4. 构建 slug → article 映射（用于连线验证）
        Map<String, Article> slugMap = articles.stream()
                .collect(Collectors.toMap(Article::getSlug, a -> a, (a, b) -> a));

        // 5. 构建节点
        List<ArticleLinkVO.Node> nodes = articles.stream()
                .map(article -> {
                    String group = article.getCategoryId() == null
                            ? "未分类"
                            : categoryNameMap.getOrDefault(article.getCategoryId(), "未分类");
                    return new ArticleLinkVO.Node(article.getSlug(), article.getTitle(), group);
                })
                .collect(Collectors.toList());


        // 4. 构建连线列表（去重）
        Set<String> linkSet = new HashSet<>();
        List<ArticleLinkVO.Link> links = new ArrayList<>();

        for (Article article : articles) {
            String content = article.getContent();
            if (content == null) continue;
            Matcher matcher = WIKI_LINK_PATTERN.matcher(content);
            while (matcher.find()) {
                String targetSlug = matcher.group(1).trim();
                // 目标存在且不是自身
                if (!targetSlug.equals(article.getSlug()) && slugMap.containsKey(targetSlug)) {
                    String key = article.getSlug() + "→" + targetSlug;
                    if (!linkSet.contains(key)) {
                        linkSet.add(key);
                        links.add(new ArticleLinkVO.Link(article.getSlug(), targetSlug));
                    }
                }
            }
        }
        System.out.println(nodes);
        System.out.println(links);
        return new ArticleLinkVO(nodes, links);
    }

    @Override
    public ArticleDetailVO getArticleSlug(String slug) {
        // 1. 查文章
        Article article = articleMapper.selectOne(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getSlug, slug)
                        .eq(Article::getIsPublished, 1)
        );
        if (article == null) {
            throw new BusinessException("文章不存在");
        }

        // 2. 查询标签列表
        List<String> tags = articleTagMapper.selectList(
                        new LambdaQueryWrapper<ArticleTag>()
                                .eq(ArticleTag::getArticleId, article.getId())
                ).stream()
                .map(at -> tagMapper.selectById(at.getTagId()).getName())
                .collect(Collectors.toList());

        // 3. 上一篇
        ArticleDetailVO.ArticleLink prev = null;
        Article prevArticle = articleMapper.selectOne(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getIsPublished, 1)
                        .lt(Article::getCreatedAt, article.getCreatedAt())
                        .orderByDesc(Article::getCreatedAt)
                        .last("LIMIT 1")
                        .select(Article::getSlug, Article::getTitle)
        );
        if (prevArticle != null) {
            prev = new ArticleDetailVO.ArticleLink(prevArticle.getSlug(), prevArticle.getTitle());
        }

        // 4. 下一篇
        ArticleDetailVO.ArticleLink next = null;
        Article nextArticle = articleMapper.selectOne(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getIsPublished, 1)
                        .gt(Article::getCreatedAt, article.getCreatedAt())
                        .orderByAsc(Article::getCreatedAt)
                        .last("LIMIT 1")
                        .select(Article::getSlug, Article::getTitle)
        );
        if (nextArticle != null) {
            next = new ArticleDetailVO.ArticleLink(nextArticle.getSlug(), nextArticle.getTitle());
        }

        // 5. 组装 VO
        /*ArticleDetailVO vo = new ArticleDetailVO();
        vo.setTitle(article.getTitle());
        vo.setSlug(article.getSlug());
        vo.setContent(article.getContent());
        vo.setTags(tags);
        vo.setCreatedAt(article.getCreatedAt());
        vo.setUpdatedAt(article.getUpdatedAt());
        vo.setPrevArticle(prev);
        vo.setNextArticle(next);*/
        ArticleDetailVO vo = BeanUtil.copyProperties(article, ArticleDetailVO.class);
        vo.setTags(tags);
        vo.setPrevArticle(prev);
        vo.setNextArticle(next);
        return vo;
    }

    /*
      获取分类名称（建议替换为真实的 CategoryMapper 查询）
     */
    /*private String getCategoryNameOrDefault(Long categoryId) {
        if (categoryId == null) return "未分类";
        // TODO: 注入 CategoryMapper 获取真实分类名
        // Category category = categoryMapper.selectById(categoryId);
        // return category != null ? category.getName() : "未分类";
        return "分类-" + categoryId; // 临时占位
    }*/
}
