package com.ll.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ll.blog.mapper.StatusMapper;
import com.ll.blog.model.po.Status;
import com.ll.blog.model.vo.StatusNowVO;
import com.ll.blog.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusMapper statusMapper;

    @Override
    public StatusNowVO getStatusNow(Long userId) {
        Status status = statusMapper.selectOne(
                new LambdaQueryWrapper<Status>().eq(Status::getUserId, userId)
        );
        if (status == null) {
            return new StatusNowVO(null, null, null);
        }

        // 组装 Music
        StatusNowVO.Music music = null;
        if (status.getMusicTitle() != null) {
            music = new StatusNowVO.Music(
                    status.getMusicTitle(),
                    status.getMusicArtist(),
                    status.getMusicCover(),
                    status.getMusicUrl()
            );
        }

        // 组装 Reading
        StatusNowVO.Reading reading = null;
        if (status.getReadingTitle() != null) {
            reading = new StatusNowVO.Reading(
                    status.getReadingTitle(),
                    status.getReadingCover()
            );
        }

        return new StatusNowVO(music, reading, status.getLocation());
    }
}
