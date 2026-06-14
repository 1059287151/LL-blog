package com.ll.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 时间线条目 VO（对应 blog.json 里的 TimelineItem）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimelineItemVO {
    private String year;    // 如 "2025"
    private String title;   // 如 "开始写博客"
    private String desc;    // 如 "搭建了这个数字分身博客..."
}
