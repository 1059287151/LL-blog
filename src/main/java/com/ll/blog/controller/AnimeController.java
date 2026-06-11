package com.ll.blog.controller;

import com.ll.blog.model.dto.AnimePageQueryDTO;
import com.ll.blog.model.vo.AnimeDetailVO;
import com.ll.blog.model.vo.AnimePageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.result.Result;
import com.ll.blog.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public Result<PageResult<AnimePageQueryVO>> page(AnimePageQueryDTO dto) {
        log.info("分页查询番剧{}", dto);
        PageResult<AnimePageQueryVO> page = animeService.page(dto);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<AnimeDetailVO> getById(@PathVariable Long id) {
        log.info("查询番剧详情 id={}", id);
        AnimeDetailVO vo = animeService.getById(id);
        return Result.success(vo);
    }

}
