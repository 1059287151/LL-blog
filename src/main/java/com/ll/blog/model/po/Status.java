package com.ll.blog.model.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 此刻状态（表中只有一行，id 固定为 1）
 */
@Data
@TableName("statuses")
public class Status {
    @TableId(type = IdType.AUTO)   // 其实 id=1 固定，但可不用自增，这里仍保持
    private Long id;
    private Long userId;          // 新增，关联用户
    private String musicTitle;
    private String musicArtist;
    private String musicCover;
    private String musicUrl;
    private String readingTitle;
    private String readingCover;
    private String location;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
