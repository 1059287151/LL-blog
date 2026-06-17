package com.ll.blog.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.ll.blog.model.dto.UserDTO;
import com.ll.blog.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.Map;
import java.util.concurrent.TimeUnit;
import static com.ll.blog.utils.RedisConstant.LOGIN_USER_TTL;
import static com.ll.blog.utils.RedisConstant.TOKEN_PREFIX;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RefreshInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 可删
        String path = request.getRequestURI();
        if ("/auth/login".equals(path) || path.startsWith("/auth/login")) {
            return true; // 登录接口直接放行，不处理 token
        }
        // 获取请求头token
        String token = request.getHeader("authorization");
        if(StrUtil.isBlank(token)){
            log.warn("请求头 Authorization 为空，URL = {}", request.getRequestURI());
            return true;
        }
        // 去掉 Bearer 前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        // 基于token获取redis中的用户
        String key = TOKEN_PREFIX + token;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
        // 判断用户是否存在
        if(userMap.isEmpty()){
            log.warn("Redis 中不存在该 token，token = {}, URL = {}", token, request.getRequestURI());
            return true;
        }
        // 将查询的数据转换为UserDTO
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
        log.info("token 验证成功，用户 = {}, URL = {}", userDTO.getUsername(), request.getRequestURI());
        // 存在，信息保存到ThreadLocal
        UserHolder.saveUser(userDTO);
        // 刷新token有效期
        stringRedisTemplate.expire(key, LOGIN_USER_TTL, TimeUnit.MINUTES);
        // 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
