package com.ll.blog.controller;

import com.ll.blog.model.dto.ArticlesPageQueryDTO;
import com.ll.blog.model.vo.ArticleDetailVO;
import com.ll.blog.model.vo.ArticleLinkVO;
import com.ll.blog.model.vo.ArticlePageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.result.Result;
import com.ll.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/links")
    public Result<ArticleLinkVO> getArticleLink(){
        log.info("获取文章双链关系(知识图谱)");
        ArticleLinkVO vo = articleService.getArticleLink();
        return Result.success(vo);
    }

    @GetMapping("/{slug}")
    public Result<ArticleDetailVO> getArticleSlug(@PathVariable String slug){
        log.info("获取文章{}", slug);
        ArticleDetailVO vo = articleService.getArticleSlug(slug);
        return Result.success(vo);
    }

}
