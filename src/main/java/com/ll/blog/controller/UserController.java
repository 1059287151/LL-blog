package com.ll.blog.controller;

import com.ll.blog.model.dto.UserDTO;
import com.ll.blog.model.vo.LoginVO;
import com.ll.blog.model.vo.UserAnimeVO;
import com.ll.blog.result.Result;
import com.ll.blog.service.AnimeService;
import com.ll.blog.service.UserService;
import com.ll.blog.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AnimeService animeService;

    @GetMapping("/me")
    public Result<LoginVO.UserVO> getCurrentUser(){
        UserDTO currentUser = UserHolder.getUser();
        LoginVO.UserVO vo = new LoginVO.UserVO(currentUser.getId(),currentUser.getUsername(), currentUser.getNickname(),currentUser.getAvatar());
        return Result.success(vo);
    }

    @GetMapping("/animes")
    public Result<List<UserAnimeVO>> getUserAnimeList(@RequestParam(required = false) Integer watchStatus) {
        log.info("查询用户追番列表 watchStatus={}", watchStatus);
        List<UserAnimeVO> list = animeService.getUserAnimeList(watchStatus);
        return Result.success(list);
    }

}
