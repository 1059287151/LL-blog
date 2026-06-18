package com.ll.blog.config;

import com.ll.blog.interceptor.LoginInterceptor;
import com.ll.blog.interceptor.RefreshInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/error",                 // Spring Boot 错误转发路径，必须放行
                        "/auth/**",               // 所有认证接口公开（登录/退出）
                        "/articles/**",           // 文章相关接口公开
                        "/notes/**",              // 短内容接口公开
                        "/ai/**",
                        "/lab/**",
                        "/animes/**",
                        "/about/**",
                        "/upload/**",
                        "/friend-link/**",
                        "/footprint/**"
                ).order(1);
        // token刷新拦截器
        registry.addInterceptor(refreshInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/error",                 // Spring Boot 错误转发路径
                        "/auth/**"                // 登录接口不需要处理 token
                )
                .order(0);
    }

    // ----------------- 新增：CORS 跨域配置 -----------------
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许所有接口
                .allowedOrigins(
                        "https://1059287151.github.io",
                        "http://localhost:5173",   // 本地 Vite 开发地址
                        "http://localhost:8080"    // 本地后端测试（可选）
                ) // 你的前端域名
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);  // 如果前端需要携带 cookie/token，必须为 true
    }
}
