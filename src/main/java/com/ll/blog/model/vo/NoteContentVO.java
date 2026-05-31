package com.ll.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteContentVO {
    private Long id;
    private String content;
    private LocalDateTime createAt;
    private Integer likes;
    private Boolean isLiked;
}
