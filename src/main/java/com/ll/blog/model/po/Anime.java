package com.ll.blog.model.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("animes")
public class Anime {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String cover;
    private String summary;
    private String type;
    private String season;          // 新增，放送季度
    private BigDecimal rating;
    private LocalDate airDate;
    @TableField("production_status")
    private Integer status;         // 0-未知,1-连载,2-完结

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}