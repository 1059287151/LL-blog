package com.ll.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnimeVO {
    private Long id;                      // user_animes 表主键
    private AnimeBriefVO anime;           // 关联的番剧元数据
    private Integer watchStatus;
    private Integer episodesWatched;
    private BigDecimal rating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
