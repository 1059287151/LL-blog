package com.ll.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ll.blog.mapper.StatusMapper;
import com.ll.blog.model.dto.StatusNowDTO;
import com.ll.blog.model.po.Status;
import com.ll.blog.model.vo.StatusNowVO;
import com.ll.blog.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Override
    public void updateStatusNow(Long userId, StatusNowDTO dto) {
        Status existing = statusMapper.selectOne(
                new LambdaQueryWrapper<Status>().eq(Status::getUserId, userId)
        );

        StatusNowDTO.Music music = dto.getMusic();
        StatusNowDTO.Reading reading = dto.getReading();

        if (existing == null) {
            // 首次设置，直接插入
            Status status = new Status();
            status.setUserId(userId);
            if (music != null) {
                status.setMusicTitle(music.getTitle());
                status.setMusicArtist(music.getArtist());
                status.setMusicCover(music.getCover());
                status.setMusicUrl(music.getUrl());
            }
            if (reading != null) {
                status.setReadingTitle(reading.getTitle());
                status.setReadingCover(reading.getCover());
            }
            status.setLocation(dto.getLocation());
            statusMapper.insert(status);
            return;
        }

        // PUT 语义：整体替换，未提供的字段置空。
        // 使用 LambdaUpdateWrapper 显式 set，确保 null 值也会写入（updateById 默认忽略 null）。
        LambdaUpdateWrapper<Status> update = new LambdaUpdateWrapper<Status>()
                .eq(Status::getUserId, userId)
                .set(Status::getMusicTitle, music == null ? null : music.getTitle())
                .set(Status::getMusicArtist, music == null ? null : music.getArtist())
                .set(Status::getMusicCover, music == null ? null : music.getCover())
                .set(Status::getMusicUrl, music == null ? null : music.getUrl())
                .set(Status::getReadingTitle, reading == null ? null : reading.getTitle())
                .set(Status::getReadingCover, reading == null ? null : reading.getCover())
                .set(Status::getLocation, dto.getLocation())
                .set(Status::getUpdatedAt, LocalDateTime.now());
        statusMapper.update(null, update);
    }
}
