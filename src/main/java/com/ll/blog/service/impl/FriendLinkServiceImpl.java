package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ll.blog.exception.FriendLinkNullException;
import com.ll.blog.mapper.FriendLinkMapper;
import com.ll.blog.model.dto.FriendLinkDTO;
import com.ll.blog.model.dto.UserDTO;
import com.ll.blog.model.po.FriendLink;
import com.ll.blog.model.vo.FriendLinkVO;
import com.ll.blog.service.FriendLinkService;
import com.ll.blog.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FriendLinkServiceImpl implements FriendLinkService {

    private final FriendLinkMapper friendLinkMapper;

    @Override
    public List<FriendLinkVO> getFriendLinks() {
        UserDTO user = UserHolder.getUser();
        List<FriendLink> friendLinks = friendLinkMapper.selectList(new LambdaQueryWrapper<FriendLink>()
                .eq(FriendLink::getUserId, user.getId())
                .orderByDesc(FriendLink::getCreatedAt));
        return friendLinks.stream().map(friendLink -> BeanUtil.copyProperties(friendLink, FriendLinkVO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrInsertFriendLink(FriendLinkDTO dto) {
        UserDTO user = UserHolder.getUser();
        if (dto.getId() != null) {
            FriendLink existing = friendLinkMapper.selectOne(new LambdaQueryWrapper<FriendLink>()
                    .eq(FriendLink::getUserId, user.getId())
                    .eq(FriendLink::getId, dto.getId())
            );
            if (existing == null) {
                throw new FriendLinkNullException("友链记录不存在", 401);
            }
            BeanUtil.copyProperties(dto, existing, CopyOptions.create().ignoreNullValue());
            friendLinkMapper.updateById(existing);
        } else {
            FriendLink friendLink = BeanUtil.copyProperties(dto, FriendLink.class);
            friendLink.setUserId(user.getId());
            friendLinkMapper.insert(friendLink);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new FriendLinkNullException("删除id为空", 401);
        }
        UserDTO user = UserHolder.getUser();
        friendLinkMapper.delete(new LambdaQueryWrapper<FriendLink>()
                .eq(FriendLink::getUserId, user.getId())
                .eq(FriendLink::getId, id)
        );
    }
}
