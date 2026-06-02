package com.ll.blog.service;

import com.ll.blog.model.dto.AnimesPageQueryDTO;
import com.ll.blog.model.vo.AnimeDetailVO;
import com.ll.blog.model.vo.AnimePageQueryVO;
import com.ll.blog.model.vo.UserAnimeVO;
import com.ll.blog.result.PageResult;

import java.util.List;

public interface AnimeService {

    PageResult<AnimePageQueryVO> page(AnimesPageQueryDTO dto);

    AnimeDetailVO getById(Long id);

    List<UserAnimeVO> getUserAnimeList(Integer watchStatus);
}
