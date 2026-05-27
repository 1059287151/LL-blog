package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.ll.blog.mapper.NoteMapper;
import com.ll.blog.model.dto.NotesPageQueryDTO;
import com.ll.blog.model.po.Note;
import com.ll.blog.model.vo.NotePageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ll.blog.content.PageQuery.MAX_PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteMapper noteMapper;

    @Override
    public PageResult<NotePageQueryVO> page(NotesPageQueryDTO dto) {
        int page = dto.getPage() != null ? dto.getPage() : 1;
        int size = dto.getSize() != null ? dto.getSize() : 20;
        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        }

        Page<Note> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Note> wrapper = new LambdaQueryWrapper<Note>()
                .orderByDesc(Note::getCreatedAt);

        Page<Note> notePage = noteMapper.selectPage(pageParam, wrapper);

        List<NotePageQueryVO> vos = BeanUtil.copyToList(notePage.getRecords(), NotePageQueryVO.class);

        return new PageResult<>(notePage.getTotal(), vos);
    }
}
