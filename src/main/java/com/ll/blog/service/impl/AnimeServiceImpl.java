package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ll.blog.exception.AnimeNotExist;
import com.ll.blog.exception.BusinessException;
import com.ll.blog.mapper.AnimeMapper;
import com.ll.blog.mapper.UserAnimeMapper;
import com.ll.blog.model.dto.AnimeRecordDTO;
import com.ll.blog.model.dto.AnimesPageQueryDTO;
import com.ll.blog.model.dto.UserDTO;
import com.ll.blog.model.po.Anime;
import com.ll.blog.model.po.UserAnime;
import com.ll.blog.model.vo.AnimeBriefVO;
import com.ll.blog.model.vo.AnimeDetailVO;
import com.ll.blog.model.vo.AnimePageQueryVO;
import com.ll.blog.model.vo.UserAnimeVO;
import com.ll.blog.result.PageResult;
import com.ll.blog.service.AnimeService;
import com.ll.blog.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ll.blog.constant.PageQueryConstant.MAX_PAGE_SIZE;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnimeServiceImpl implements AnimeService {

    private final AnimeMapper animeMapper;
    private final UserAnimeMapper userAnimeMapper;

    @Override
    public PageResult<AnimePageQueryVO> page(AnimesPageQueryDTO dto) {
        int page = dto.getPage() != null ? dto.getPage() : 1;
        int size = dto.getSize() != null ? dto.getSize() : 10;
        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        }

        Page<Anime> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Anime> wrapper = new LambdaQueryWrapper<Anime>()
                .eq(dto.getProductionStatus() != null, Anime::getStatus, dto.getProductionStatus())
                .eq(StringUtils.hasText(dto.getSeason()), Anime::getSeason, dto.getSeason())
                .eq(StringUtils.hasText(dto.getType()), Anime::getType, dto.getType())
                .like(StringUtils.hasText(dto.getKeyword()), Anime::getTitle, dto.getKeyword())
                .orderByDesc(Anime::getCreatedAt);

        Page<Anime> animePage = animeMapper.selectPage(pageParam, wrapper);
        List<AnimePageQueryVO> vos = animePage.getRecords().stream().map(a -> {
            AnimePageQueryVO vo = BeanUtil.copyProperties(a, AnimePageQueryVO.class);
            vo.setProductionStatus(a.getStatus());
            return vo;
        }).toList();
        return new PageResult<>(animePage.getTotal(), vos);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AnimeDetailVO getById(Long id) {
        Anime anime = animeMapper.selectById(id);
        if (anime == null) {
            throw new AnimeNotExist("番剧不存在");
        }
        AnimeDetailVO vo = BeanUtil.copyProperties(anime, AnimeDetailVO.class);
        vo.setProductionStatus(anime.getStatus());

        UserDTO user = UserHolder.getUser();
        if (user != null) {
            UserAnime userAnime = userAnimeMapper.selectOne(new LambdaQueryWrapper<UserAnime>()
                    .eq(UserAnime::getUserId, user.getId())
                    .eq(UserAnime::getAnimeId, id));
            if (userAnime != null) {
                AnimeDetailVO.UserRecord record = BeanUtil.copyProperties(userAnime, AnimeDetailVO.UserRecord.class);
                vo.setUserRecord(record);
            }
        }

        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserAnimeVO> getUserAnimeList(Integer watchStatus) {
        UserDTO user = UserHolder.getUser();
        LambdaQueryWrapper<UserAnime> wrapper = new LambdaQueryWrapper<UserAnime>()
                .eq(UserAnime::getUserId, user.getId())
                .eq(watchStatus != null, UserAnime::getWatchStatus, watchStatus)
                .orderByDesc(UserAnime::getCreatedAt);
        List<UserAnime> userAnimeList = userAnimeMapper.selectList(wrapper);

        return userAnimeList.stream().map(ua -> {
            UserAnimeVO vo = BeanUtil.copyProperties(ua, UserAnimeVO.class);
            Anime anime = animeMapper.selectById(ua.getAnimeId());
            if (anime != null) {
                AnimeBriefVO brief = BeanUtil.copyProperties(anime, AnimeBriefVO.class);
                brief.setProductionStatus(anime.getStatus());
                vo.setAnime(brief);
            }
            return vo;
        }).toList();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateAnimeRecord(AnimeRecordDTO dto) {
        UserDTO user = UserHolder.getUser();

        Anime anime = animeMapper.selectById(dto.getAnimeId());
        if (anime == null) {
            throw new AnimeNotExist("番剧不存在");
        }

        UserAnime existing = userAnimeMapper.selectOne(new LambdaQueryWrapper<UserAnime>()
                .eq(UserAnime::getUserId, user.getId())
                .eq(UserAnime::getAnimeId, dto.getAnimeId()));

        if (existing != null) {
            BeanUtil.copyProperties(dto, existing, CopyOptions.create().ignoreNullValue());
            userAnimeMapper.updateById(existing);
        } else {
            UserAnime userAnime = BeanUtil.copyProperties(dto, UserAnime.class);
            userAnime.setUserId(user.getId());
            userAnimeMapper.insert(userAnime);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer animeId) {
        UserDTO user = UserHolder.getUser();
        userAnimeMapper.delete(new LambdaQueryWrapper<UserAnime>()
                .eq(UserAnime::getUserId, user.getId())
                .eq(UserAnime::getAnimeId, animeId));
    }

}
