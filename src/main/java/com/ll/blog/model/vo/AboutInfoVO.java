package com.ll.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
        * 关于页信息 VO（对应 blog.json 里的 AboutInfo）
        * 作为 Result<T> 的 data 字段：Result<AboutInfoVO>
   */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutInfoVO {
    private List<TimelineItemVO> timeline;
}