package com.ll.blog.model.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("about_timeline")
public class AboutTimeline {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String year;          // 时间节点
    private String title;         // 标题

    @TableField("description")
    private String description;   // 描述

    @TableField("sort_order")
    private Integer sortOrder;    // 排序

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
