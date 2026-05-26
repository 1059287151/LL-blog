package com.ll.blog.controller;

import com.ll.blog.model.dto.NotesPageQueryDTO;
import com.ll.blog.model.vo.NotesPageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.result.Result;
import com.ll.blog.service.NotesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
@Slf4j
@RequiredArgsConstructor
public class NotesController {

    private final NotesService notesService;

    @GetMapping
    public Result<PageResult<NotesPageQueryVO>> page(NotesPageQueryDTO notesPageQueryDTO) {
        log.info("分页查询数据{}", notesPageQueryDTO);
        PageResult<NotesPageQueryVO> pageResult = notesService.page(notesPageQueryDTO);
        return Result.success(pageResult);
    }

}
