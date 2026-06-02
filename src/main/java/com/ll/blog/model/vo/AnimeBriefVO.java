package com.ll.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeBriefVO {
    private Long id;
    private String title;
    private String titleJa;
    private String cover;
    private String summary;
    private String type;
    private Integer episodesTotal;
    private String season;
    private BigDecimal rating;
    private LocalDate airDate;
    private Integer productionStatus;
}
