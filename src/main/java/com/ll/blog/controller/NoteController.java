package com.ll.blog.controller;

import com.ll.blog.model.dto.NotesPageQueryDTO;
import com.ll.blog.model.vo.NotePageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.result.Result;
import com.ll.blog.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
@Slf4j
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public Result<PageResult<NotePageQueryVO>> page(NotesPageQueryDTO notesPageQueryDTO) {
        log.info("分页查询数据{}", notesPageQueryDTO);
        PageResult<NotePageQueryVO> pageResult = noteService.page(notesPageQueryDTO);
        return Result.success(pageResult);
    }

}
