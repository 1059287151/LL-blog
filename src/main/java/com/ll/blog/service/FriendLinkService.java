package com.ll.blog.service;

import com.ll.blog.model.dto.FriendLinkDTO;
import com.ll.blog.model.vo.FriendLinkVO;

import java.util.List;

public interface FriendLinkService {
    List<FriendLinkVO> getFriendLinks();

    void saveOrInsertFriendLink(FriendLinkDTO friendLinkDTO);

    void deleteById(Long id);

}
