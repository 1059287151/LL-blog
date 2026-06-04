package com.ll.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ArticlePageQueryVO {
    private Long id;
    private String title;
    private String slug;
    private String summary;
    private String cover;
    private String[] tags;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
