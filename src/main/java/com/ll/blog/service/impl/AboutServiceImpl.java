package com.ll.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ll.blog.mapper.AboutMapper;
import com.ll.blog.model.po.AboutTimeline;
import com.ll.blog.model.vo.AboutInfoVO;
import com.ll.blog.model.vo.TimelineItemVO;
import com.ll.blog.service.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AboutServiceImpl implements AboutService {

    private final AboutMapper aboutMapper;

    @Override
    public AboutInfoVO getTimeline() {
        List<AboutTimeline> poList = aboutMapper.selectList(
                new LambdaQueryWrapper<AboutTimeline>()
                        .orderByAsc(AboutTimeline::getSortOrder)
        );

        List<TimelineItemVO> timeline = poList.stream()
                .map(po -> new TimelineItemVO(po.getYear(), po.getTitle(), po.getDescription()))
                .collect(Collectors.toList());

        return new AboutInfoVO(timeline);
    }
}
