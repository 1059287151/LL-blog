package com.ll.blog.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotePageQueryVO {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Integer likes;
    private Boolean isLiked = false;

}
