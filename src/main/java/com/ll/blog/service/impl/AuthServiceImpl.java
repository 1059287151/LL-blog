package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ll.blog.exception.BusinessException;
import com.ll.blog.model.dto.RegisterDTO;
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

    @Override
    public LoginVO.UserVO register(RegisterDTO dto) {
        // 1. 基础校验
        if (dto.getNickname() == null || dto.getNickname().trim().isEmpty()) {
            throw new BusinessException("请输入昵称");
        }
        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
            throw new BusinessException("请输入用户名");
        }
        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            throw new BusinessException("密码至少需要6个字符");
        }
        // 2. 用户名查重（username 有唯一约束兜底，这里先查重给友好提示）
        if (userService.findByUsername(dto.getUsername()) != null) {
            throw new BusinessException("用户名已存在");
        }
        // 3. 创建用户：密码加密存储；昵称写入 nickname（映射到 role 列）
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setNickname(dto.getNickname());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        userService.save(user); // createdAt/updatedAt 由 MyMetaObjectHandler 自动填充
        // 4. 返回创建后的用户信息（不含 token，前端注册后跳转登录页）
        return BeanUtil.copyProperties(user, LoginVO.UserVO.class);
    }

    /**
     * 退出登录：从 Redis 中删除 Token
     */
    @Override
    public void logout(String token) {
        tokenRedisService.removeToken(token);
    }
}
