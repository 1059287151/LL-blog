package com.ll.blog.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文章-标签关联表（多对多中间表）
 */
@Data
@TableName("article_tags")
public class ArticleTag {
    /**
     * 注意：联合主键可使用 MP 的 @TableId 或 @MppMultiId（MyBatis-Plus 多主键插件）
     * 此处简单用两个字段表示，实际使用时不需单独的 Mapper，通过 Article 和 Tag 的关联查询处理
     */
    private Long articleId;
    private Long tagId;
}
