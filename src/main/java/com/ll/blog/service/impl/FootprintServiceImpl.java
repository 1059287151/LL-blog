package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ll.blog.exception.FootprintNullException;
import com.ll.blog.mapper.FootprintMapper;
import com.ll.blog.model.dto.FootprintUpdateDTO;
import com.ll.blog.model.dto.UserDTO;
import com.ll.blog.model.po.Footprint;
import com.ll.blog.model.vo.FootprintVO;
import com.ll.blog.service.FootprintService;
import com.ll.blog.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FootprintServiceImpl implements FootprintService {

    private final FootprintMapper footprintMapper;

    @Override
    public List<FootprintVO> getCurrentFootprint() {
        UserDTO user = UserHolder.getUser();
        List<Footprint> footprints = footprintMapper.selectList(new LambdaQueryWrapper<Footprint>()
                .eq(Footprint::getUserId, user.getId())
                .orderByDesc(Footprint::getDate));
        return footprints.stream()
                .map(footprint -> BeanUtil.copyProperties(footprint, FootprintVO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFootprint(FootprintUpdateDTO dto) {
        UserDTO user = UserHolder.getUser();

        if (dto.getId() != null) {
            // 更新：查询属于当前用户的足迹记录
            Footprint existing = footprintMapper.selectOne(new LambdaQueryWrapper<Footprint>()
                    .eq(Footprint::getId, dto.getId())
                    .eq(Footprint::getUserId, user.getId()));
            if (existing == null) {
                throw new FootprintNullException("足迹记录不存在", 401);
            }
            BeanUtil.copyProperties(dto, existing, CopyOptions.create().ignoreNullValue());
            footprintMapper.updateById(existing);
        } else {
            // 新增
            Footprint footprint = BeanUtil.copyProperties(dto, Footprint.class);
            footprint.setUserId(user.getId());
            footprintMapper.insert(footprint);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new FootprintNullException("删除id为空");
        }
        UserDTO user = UserHolder.getUser();
        footprintMapper.delete(new LambdaQueryWrapper<Footprint>()
                .eq(Footprint::getUserId, user.getId())
                .eq(Footprint::getId, id)
        );
    }
}
