package com.ll.blog.controller;

import com.ll.blog.model.dto.LoginDTO;
import com.ll.blog.model.dto.RegisterDTO;
import com.ll.blog.model.vo.LoginVO;
import com.ll.blog.result.Result;
import com.ll.blog.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO request) {
        LoginVO loginVO = authService.login(request.getUsername(), request.getPassword());
        return Result.success(loginVO);
    }

    @PostMapping("/register")
    public Result<LoginVO.UserVO> register(@RequestBody RegisterDTO request) {
        LoginVO.UserVO userVO = authService.register(request);
        return Result.success(userVO);
    }

    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        log.info("logout: 原始 header = {}, 提取 token = {}", authHeader, token);
        authService.logout(token);
        return Result.success();
    }

}
