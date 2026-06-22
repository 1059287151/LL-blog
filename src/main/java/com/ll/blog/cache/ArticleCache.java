package com.ll.blog.cache;

import com.ll.blog.config.HexoConfig;
import com.ll.blog.utils.MarkdownParser;
import cn.hutool.core.io.IoUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ArticleCache implements CommandLineRunner {
    private final HexoConfig hexoConfig;
    @Getter
    private Map<String, MarkdownParser.ArticleMeta> slugMap = new ConcurrentHashMap<>();
    @Getter
    private List<MarkdownParser.ArticleMeta> sortedList = new ArrayList<>();

    @Override
    public void run(String... args) {
        refresh();
    }

    public synchronized void refresh() {
        log.info("刷新文章缓存...");
        slugMap.clear();
        sortedList.clear();

        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(hexoConfig.getPostsPath() + "**/*.md");
            for (Resource res : resources) {
                String raw = IoUtil.read(res.getInputStream(), StandardCharsets.UTF_8);
                String fileName = res.getFilename();
                MarkdownParser.ArticleMeta meta = MarkdownParser.parse(raw, fileName);
                if (meta != null && meta.slug() != null) {
                    slugMap.put(meta.slug(), meta);
                    sortedList.add(meta);
                }
            }
            // 按日期倒序排列
            sortedList.sort(Comparator.comparing(MarkdownParser.ArticleMeta::date,
                    Comparator.nullsLast(Comparator.reverseOrder())));
            log.info("缓存完成，共 {} 篇文章", slugMap.size());
        } catch (Exception e) {
            log.error("初始化文章缓存失败", e);
        }
    }

    public MarkdownParser.ArticleMeta getBySlug(String slug) {
        return slugMap.get(slug);
    }

    // 分页筛选
    public List<MarkdownParser.ArticleMeta> filter(String tag, String keyword) {
        return sortedList.stream()
                .filter(m -> tag == null || tag.isEmpty() || m.tags().contains(tag))
                .filter(m -> keyword == null || keyword.isEmpty() ||
                        m.title().contains(keyword) || m.content().contains(keyword))
                .collect(Collectors.toList());
    }
}
