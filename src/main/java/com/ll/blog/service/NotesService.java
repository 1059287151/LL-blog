package com.ll.blog.service;


import com.ll.blog.model.dto.NotesPageQueryDTO;
import com.ll.blog.model.vo.NotesPageQueryVO;
import com.ll.blog.result.PageResult;

public interface NotesService {

    PageResult<NotesPageQueryVO> page(NotesPageQueryDTO dto);

}
