package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.ll.blog.mapper.NoteMapper;
import com.ll.blog.model.dto.NotesPageQueryDTO;
import com.ll.blog.model.po.Note;
import com.ll.blog.model.vo.NotesPageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {

    private static final int MAX_PAGE_SIZE = 100;

    private final NoteMapper noteMapper;

    @Override
    public PageResult<NotesPageQueryVO> page(NotesPageQueryDTO dto) {
        int page = dto.getPage() != null ? dto.getPage() : 1;
        int size = dto.getSize() != null ? dto.getSize() : 20;
        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        }

        Page<Note> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Note> wrapper = new LambdaQueryWrapper<Note>()
                .orderByDesc(Note::getCreatedAt);

        Page<Note> notePage = noteMapper.selectPage(pageParam, wrapper);

        List<NotesPageQueryVO> vos = BeanUtil.copyToList(notePage.getRecords(), NotesPageQueryVO.class);

        return new PageResult<>(notePage.getTotal(), vos);
    }
}
