package com.ll.blog.model.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_animes")
public class UserAnime {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long animeId;
    private Integer watchStatus;      // 0-想看,1-在看,2-已看完,3-弃了
    private Integer episodesWatched;
    private BigDecimal rating;        // 个人评分
    private String comment;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}