package com.ll.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ll.blog.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.ll.blog.utils.RedisConstant.TOKEN_PREFIX;

@Service
@RequiredArgsConstructor
public class TokenRedisService {

    private final StringRedisTemplate stringRedisTemplate;

    // /**
    //  * 存储 Token，关联 userId，并设置过期时间
    //  * @param token   Token 字符串
    //  * @param userId  用户 ID
    //  * @param expireSeconds 过期时间（秒）
    //  */
    // public void storeToken(String token,Long userId, long expireSeconds){
    //     String key = TOKEN_PREFIX + token;
    //     stringRedisTemplate.opsForValue().set(key, userId.toString(), expireSeconds, TimeUnit.SECONDS);
    // }

    /**
     * 存储 Token，以 Hash 结构存入用户信息，并设置过期时间
     * @param token   Token 字符串
     * @param userDTO 用户信息
     * @param expireSeconds 过期时间（秒）
     */
    public void storeToken(String token, UserDTO userDTO, long expireSeconds) {
        String key = TOKEN_PREFIX + token;
        /*Map<String, String> userMap = new HashMap<>();
        userMap.put("id", userDTO.getId().toString());
        userMap.put("username", userDTO.getUsername());
        userMap.put("nickname", userDTO.getNickname());
        userMap.put("avatar", userDTO.getAvatar());*/

        // 这里必须要处理下数据，因为这里有long类型的数据，而我们用的string存入，必须要string类型的所以要处理数据
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO,new HashMap<>(),
                CopyOptions.create().setIgnoreNullValue(true).setFieldValueEditor((fileName, fileValue) ->fileValue == null ? null : fileValue.toString()));
        stringRedisTemplate.opsForHash().putAll(key, userMap);
        stringRedisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
    }

    /**
     * 校验 Token 是否有效（存在且未过期）
     * @return 有效返回 true
     */
    public Boolean isTokenValid(String token){
        String key = TOKEN_PREFIX + token;
        Boolean exists = stringRedisTemplate.hasKey(key);
        return Boolean.TRUE.equals(exists);
    }

    // /**
    //  * 根据 Token 获取用户 ID
    //  * @return 用户 ID，若 Token 不存在则返回 null
    //  */
    // public Long getUserId(String token){
    //     String key = TOKEN_PREFIX + token;
    //     String userId = stringRedisTemplate.opsForValue().get(key);
    //     return userId != null ? Long.valueOf(userId) : null;
    // }

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
        stringRedisTemplate.delete(TOKEN_PREFIX + token);
    }
}
