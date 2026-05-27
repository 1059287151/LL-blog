package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ll.blog.mapper.ArticleMapper;
import com.ll.blog.model.dto.ArticlesPageQueryDTO;
import com.ll.blog.model.po.Article;
import com.ll.blog.model.vo.ArticlePageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ll.blog.content.PageQuery.MAX_PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Override
    public PageResult<ArticlePageQueryVO> page(ArticlesPageQueryDTO dto) {
        int page = dto.getPage() != null ? dto.getPage() : 1;
        int size = dto.getSize() != null ? dto.getSize() : 5;
        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        }
        Page<Article> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<Article>()
                .orderByDesc(true, Article::getCreatedAt);
        Page<Article> articlePage = articleMapper.selectPage(pageParam, wrapper);
        List<ArticlePageQueryVO> vos = BeanUtil.copyToList(articlePage.getRecords(), ArticlePageQueryVO.class);
        return new PageResult<>(articlePage.getTotal(), vos);
    }
}
