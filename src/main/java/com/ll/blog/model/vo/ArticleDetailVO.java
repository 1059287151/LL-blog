package com.ll.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVO {
    private String title;
    private String slug;
    private String content;              // Markdown 正文
    private List<String> tags;           // 标签名称数组
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ArticleLink prevArticle;     // 上一篇，可为 null
    private ArticleLink nextArticle;     // 下一篇，可为 null

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleLink {
        private String slug;
        private String title;
    }
}
