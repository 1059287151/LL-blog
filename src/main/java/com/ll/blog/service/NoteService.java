package com.ll.blog.service;


import com.ll.blog.model.dto.NotesPageQueryDTO;
import com.ll.blog.model.vo.NoteContentVO;
import com.ll.blog.model.vo.NotePageQueryVO;
import com.ll.blog.result.PageResult;

public interface NoteService {

    PageResult<NotePageQueryVO> page(NotesPageQueryDTO dto);

    NoteContentVO announceContent(String content);

}
