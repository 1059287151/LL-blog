package com.ll.blog.model.dto;

import lombok.Data;

@Data
public class AnimePageQueryDTO {
    private Integer page;
    private Integer size;
    private String season;
    private String type;
    private Integer productionStatus;
    private String keyword;
}
