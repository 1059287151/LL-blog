package com.ll.blog.service;

import com.ll.blog.model.dto.ArticlesPageQueryDTO;
import com.ll.blog.model.vo.ArticleDetailVO;
import com.ll.blog.model.vo.ArticleLinkVO;
import com.ll.blog.model.vo.ArticlePageQueryVO;
import com.ll.blog.result.PageResult;

public interface ArticleService {

    PageResult<ArticlePageQueryVO> page(ArticlesPageQueryDTO articlesPageQueryDTO);

    ArticleLinkVO getArticleLink();

    ArticleDetailVO getArticleSlug(String slug);
}
