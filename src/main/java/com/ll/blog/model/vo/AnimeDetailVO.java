package com.ll.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AnimeDetailVO {
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
    private UserRecord userRecord;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRecord {
        private Long id;
        private Integer watchStatus;
        private Integer episodesWatched;
        private BigDecimal rating;
        private String comment;
    }
}
