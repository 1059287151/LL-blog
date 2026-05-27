package com.ll.blog.model.dto;

import lombok.Data;

@Data
public class ArticlesPageQueryDTO {
    private String keyword;
    private Integer page;
    private Integer size;
    private String tag;
}
