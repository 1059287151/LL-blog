package com.ll.blog.model.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AnimePageQueryVO {
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
