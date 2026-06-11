package com.ll.blog.controller;


import com.ll.blog.model.dto.FriendLinkDTO;
import com.ll.blog.model.vo.FriendLinkVO;
import com.ll.blog.result.Result;
import com.ll.blog.service.FriendLinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend-links")
@Slf4j
@RequiredArgsConstructor
public class FriendLinkController {

    private final FriendLinkService friendLinkService;

    @GetMapping
    public Result<List<FriendLinkVO>> getFriendLinksList(){
        log.info("获取友链列表");
        return Result.success(friendLinkService.getFriendLinks());
    }

    @PostMapping
    public Result<Void> saveOrUpdateFriendLink(@RequestBody FriendLinkDTO friendLinkDTO){
        log.info("更新或插入友链:{}", friendLinkDTO);
        friendLinkService.saveOrInsertFriendLink(friendLinkDTO);
        return Result.success();
    }

    @DeleteMapping
    public Result<Void> deleteFriendLink(@RequestParam Long id){
        log.info("删除友链, id:{}",id);
        friendLinkService.deleteById(id);
        return Result.success();
    }
}
