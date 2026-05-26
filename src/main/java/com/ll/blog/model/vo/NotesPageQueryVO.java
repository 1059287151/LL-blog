package com.ll.blog.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotesPageQueryVO {

    private Long id;
    private String content;
    private LocalDateTime createAt;
    private Integer likes;
    private Boolean isLiked = false;

}
