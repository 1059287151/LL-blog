package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ll.blog.mapper.AnimeMapper;
import com.ll.blog.model.dto.AnimesPageQueryDTO;
import com.ll.blog.model.po.Anime;
import com.ll.blog.model.vo.AnimePageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ll.blog.constant.PageQueryConstant.MAX_PAGE_SIZE;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnimeServiceImpl implements AnimeService {

    private final AnimeMapper animeMapper;

    @Override
    public PageResult<AnimePageQueryVO> page(AnimesPageQueryDTO dto) {
        int page = dto.getPage() != null ? dto.getPage() : 1;
        int size = dto.getSize() != null ? dto.getSize() : 10;
        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        }

        Page<Anime> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Anime> wrapper = new LambdaQueryWrapper<Anime>()
                .eq(dto.getProductionStatus() != null, Anime::getStatus, dto.getProductionStatus())
                .eq(StringUtils.hasText(dto.getSeason()), Anime::getSeason, dto.getSeason())
                .eq(StringUtils.hasText(dto.getType()), Anime::getType, dto.getType())
                .like(StringUtils.hasText(dto.getKeyword()), Anime::getTitle, dto.getKeyword())
                .orderByDesc(Anime::getCreatedAt);

        Page<Anime> animePage = animeMapper.selectPage(pageParam, wrapper);
        List<AnimePageQueryVO> vos = BeanUtil.copyToList(animePage.getRecords(), AnimePageQueryVO.class);
        return new PageResult<>(animePage.getTotal(), vos);
    }

}
