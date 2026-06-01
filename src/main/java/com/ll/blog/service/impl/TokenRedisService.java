package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ll.blog.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
// ========== 👇 新增 ==========
//import java.util.Set;
//import static com.ll.blog.utils.RedisConstant.USER_TOKENS_PREFIX;
// ========== 👆 新增 ==========
import java.util.concurrent.TimeUnit;

import static com.ll.blog.utils.RedisConstant.TOKEN_PREFIX;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenRedisService {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 存储 Token，以 Hash 结构存入用户信息，并设置过期时间
     * @param token   Token 字符串
     * @param userDTO 用户信息
     * @param expireSeconds 过期时间（秒）
     */
    public void storeToken(String token, UserDTO userDTO, long expireSeconds) {
        String key = TOKEN_PREFIX + token;
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create().setIgnoreNullValue(true).setFieldValueEditor((fileName, fileValue) -> fileValue == null ? null : fileValue.toString()));
        stringRedisTemplate.opsForHash().putAll(key, userMap);
        stringRedisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
//        // ========== 👇 新增 ==========
//        // 维护用户 token 索引，用于登录时清理旧 token
//        String userTokensKey = USER_TOKENS_PREFIX + userDTO.getId();
//        stringRedisTemplate.opsForSet().add(userTokensKey, token);
//        stringRedisTemplate.expire(userTokensKey, expireSeconds, TimeUnit.SECONDS);
//        // ========== 👆 新增 ==========
    }

//    // ========== 👇 新增 ==========
//    /**
//     * 清理用户所有旧 token（登录时调用）
//     */
//    public void removeUserTokens(Long userId) {
//        String userTokensKey = USER_TOKENS_PREFIX + userId;
//        Set<String> oldTokens = stringRedisTemplate.opsForSet().members(userTokensKey);
//        if (oldTokens != null && !oldTokens.isEmpty()) {
//            for (String oldToken : oldTokens) {
//                stringRedisTemplate.delete(TOKEN_PREFIX + oldToken);
//            }
//        }
//        stringRedisTemplate.delete(userTokensKey);
//    }
//    // ========== 👆 新增 ==========

    /**
     * 校验 Token 是否有效（存在且未过期）
     * @return 有效返回 true
     */
    public Boolean isTokenValid(String token) {
        String key = TOKEN_PREFIX + token;
        Boolean exists = stringRedisTemplate.hasKey(key);
        return Boolean.TRUE.equals(exists);
    }

    /**
     * 根据 Token 从 Hash 中获取用户 ID
     * @return 用户 ID，若 Token 不存在则返回 null
     */
    public Long getUserId(String token) {
        String key = TOKEN_PREFIX + token;
        Object userId = stringRedisTemplate.opsForHash().get(key, "id");
        return userId != null ? Long.valueOf(userId.toString()) : null;
    }

    /**
     * 删除 Token（用户退出或强制下线）
     */
    public void removeToken(String token) {
        String key = TOKEN_PREFIX + token;
        log.info("logout: 删除 Redis key = {}", key);
        stringRedisTemplate.delete(key);
    }
}
