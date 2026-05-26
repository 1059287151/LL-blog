package com.ll.blog.model.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 短内容 / 时光碎片
 */
@Data
@TableName("notes")
public class Note {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String content;
    private Long userId;
    private Integer likes;       // 冗余字段，触发器维护
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
