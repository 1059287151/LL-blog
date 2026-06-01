package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ll.blog.exception.BusinessException;
import com.ll.blog.model.dto.UserDTO;
import com.ll.blog.model.po.User;
import com.ll.blog.model.vo.LoginVO;
import com.ll.blog.service.AuthService;
import com.ll.blog.service.UserService;
import com.ll.blog.utils.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    // TODO 只剩拦截器了
    private final TokenUtil tokenUtil;
    private final TokenRedisService tokenRedisService;
    private final UserService userService;  // 你自己的用户查询服务
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${auth.token.expire:7200}")
    private long tokenExpireSeconds;

    @Override
    public LoginVO login(String username, String password) {
        User user = userService.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPasswordHash())) {
            //TODO 异常信息类要换
            throw new BusinessException("用户名或密码错误");
        }
        // 1. 生成 Token
        String token = tokenUtil.generatorToken();
//        // ========== 👇 新增 ==========
//        // 2. 清理该用户旧 token，避免多端登录残留
//        tokenRedisService.removeUserTokens(user.getId());
//        // ========== 👆 新增 ==========
        // 2. 构建 UserDTO，以 Hash 结构存入 Redis
        //UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getNickname(), user.getAvatar());
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        tokenRedisService.storeToken(token, userDTO, tokenExpireSeconds);
        // 4. 返回 Token 和用户信息
        //LoginVO.UserVO userVO = new LoginVO.UserVO(user.getId(), user.getUsername(), user.getNickname(), user.getAvatar());
        LoginVO.UserVO userVO = BeanUtil.copyProperties(user, LoginVO.UserVO.class);
        return new LoginVO(token, userVO);
    }

    /**
     * 退出登录：从 Redis 中删除 Token
     */
    @Override
    public void logout(String token) {
        tokenRedisService.removeToken(token);
    }
}
