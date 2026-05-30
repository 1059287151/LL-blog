package com.ll.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabRandomVO {
    private String name;         // 工具标识，如 "fluid-particles"
    private Object initialData;  // 初始数据，可留空对象
}
