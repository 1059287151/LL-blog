package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.ll.blog.exception.BusinessException;
import com.ll.blog.exception.ContentBeyondException;
import com.ll.blog.exception.ContentNotNullException;
import com.ll.blog.mapper.NoteLikeMapper;
import com.ll.blog.mapper.NoteMapper;
import com.ll.blog.model.dto.NotesPageQueryDTO;
import com.ll.blog.model.po.Note;
import com.ll.blog.model.po.NoteLike;
import com.ll.blog.model.vo.NoteContentVO;
import com.ll.blog.model.vo.NoteLikeVO;
import com.ll.blog.model.vo.NotePageQueryVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.service.NoteService;
import com.ll.blog.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.ll.blog.constant.PageQueryConstant.MAX_PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteMapper noteMapper;
    private final NoteLikeMapper noteLikeMapper;

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

    @Override
    public NoteContentVO announceContent(String content) {
        // 1. 校验内容
        if (!StringUtils.hasText(content)) {
            throw new ContentNotNullException("内容不能为空", 400);
        }
        if (content.length() > 280) {
            throw new ContentBeyondException("内容最多280个字符", 400);
        }

        // 2. 获取当前用户ID（由拦截器存入 ThreadLocal）
        Long userId = UserHolder.getUser().getId();

        // 3. 构建实体并插入数据库
        Note note = new Note();
        note.setContent(content);
        note.setUserId(userId);
        note.setLikes(0);
        note.setCreatedAt(LocalDateTime.now());
        // MyBatis-Plus 插入后会自动回填 note.id
        noteMapper.insert(note);

        NoteContentVO VO = BeanUtil.copyProperties(note, NoteContentVO.class);
        VO.setIsLiked(false);
        return VO;
    }

    @Override
    public NoteLikeVO likeStatus(Long id) {
        // 1. 获取当前用户ID
        Long userId = UserHolder.getUser().getId();

        // 2. 校验短内容是否存在
        Note note = noteMapper.selectById(id);
        if (note == null) {
            throw new ContentNotNullException("短内容不存在", 404);
        }

        // 3. 查询是否已点赞（利用唯一约束）
        NoteLike noteLike = noteLikeMapper.selectOne(
                new LambdaQueryWrapper<NoteLike>()
                        .eq(NoteLike::getNoteId, id)
                        .eq(NoteLike::getUserId, userId)
        );

        boolean isLiked;
        if (noteLike != null) {
            // 已点赞 → 取消点赞
            noteLikeMapper.deleteById(noteLike.getId());
            isLiked = false;
        } else {
            // 未点赞 → 添加点赞
            noteLike = new NoteLike();
            noteLike.setNoteId(id);
            noteLike.setUserId(userId);
            noteLikeMapper.insert(noteLike);
            isLiked = true;
        }

        // 4. 重新查询最新的点赞数（数据库触发器会自动维护 notes.likes）
        Note updatedNote = noteMapper.selectById(id);
        return new NoteLikeVO(updatedNote.getLikes(), isLiked);
    }
}
