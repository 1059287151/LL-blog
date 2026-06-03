package com.ll.blog.model.dto;

import lombok.Data;

@Data
public class AnimesPageQueryDTO {
    private Integer page;
    private Integer size;
    private String season;
    private String type;
    private Integer productionStatus;
    private String keyword;
}
