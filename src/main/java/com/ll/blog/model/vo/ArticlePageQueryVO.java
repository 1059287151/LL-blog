package com.ll.blog.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticlePageQueryVO {
    private Long id;
    private String title;
    private String slug;
    private String summary;
    private String cover;
    private String[] tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
