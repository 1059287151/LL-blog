package com.ll.blog.config;

import com.ll.blog.interceptor.LoginInterceptor;
import com.ll.blog.interceptor.RefreshInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final RefreshInterceptor refreshInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(
                        "/auth/login",
                        "/articles/**",          // 文章相关接口公开
                        "/notes",                 // 获取短内容列表公开（GET，但同路径 POST 会多一次校验？需注意）
                        "/status/now",            // 获取状态公开（多用户版需要改？）
                        "/ai/**",
                        "/lab/**"
                        // 这里根据实际需求调整，允许不需要登录就能访问的接口
                ).order(1);
        // token刷新拦截器
        registry.addInterceptor(refreshInterceptor)
                .addPathPatterns("/**")
                .order(0);
    }
}
