package com.ll.blog.service;

import com.ll.blog.model.dto.AnimesPageQueryDTO;
import com.ll.blog.model.vo.AnimePageQueryVO;
import com.ll.blog.result.PageResult;

public interface AnimeService {

    PageResult<AnimePageQueryVO> page(AnimesPageQueryDTO dto);
}
