package com.ll.blog.controller;

import com.ll.blog.model.dto.ArticlesPageQueryDTO;
import com.ll.blog.model.vo.ArticlePageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.result.Result;
import com.ll.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@Slf4j
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public Result<PageResult<ArticlePageQueryVO>> page(ArticlesPageQueryDTO articlesPageQueryDTO){
        log.info("分页查询{}", articlesPageQueryDTO);
        PageResult<ArticlePageQueryVO> page = articleService.page(articlesPageQueryDTO);
        return Result.success(page);
    }

}
