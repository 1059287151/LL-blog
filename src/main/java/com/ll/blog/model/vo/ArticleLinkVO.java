package com.ll.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLinkVO {
    private List<Node> nodes;
    private List<Link> links;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Node {
        private String id;      // 文章 slug
        private String label;   // 文章标题
        private String group;   // 分类名称（或自定义分组）
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Link {
        private String source;  // 引用方 slug
        private String target;  // 被引用方 slug
    }
}
