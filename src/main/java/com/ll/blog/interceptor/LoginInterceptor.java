package com.ll.blog.interceptor;

import com.ll.blog.exception.LoginNotTokenException;
import com.ll.blog.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    // 基于redis设置拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 判断是否要拦截
        if(UserHolder.getUser() == null){
            throw new LoginNotTokenException("请先登录", 401);
        }
        // 有用户，放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        // 移出用户
        UserHolder.removeUser();
    }
}
