package com.ll.blog.handler;

import com.ll.blog.exception.BaseException;
import com.ll.blog.exception.BusinessException;
import com.ll.blog.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalException {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex, HttpServletResponse response){
        log.error("异常信息：{}", ex.getMessage());
        response.setStatus(ex.getCode());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    public Result<Void> exceptionHandler(BusinessException ex){
        log.info("文章异常信息:{}",ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result<Void> exceptionHandler(MissingServletRequestParameterException ex){
        log.warn("缺少请求参数: {}", ex.getMessage());
        return Result.error("缺少必要参数: " + ex.getParameterName());
    }

    /**
     * 兜底异常处理，防止 500 时返回默认的 Spring Boot 错误格式
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Void> exceptionHandler(Exception ex, HttpServletResponse response){
        log.error("未处理的异常: {}", ex.getMessage(), ex);
        response.setStatus(500);
        return Result.error("服务器内部错误: " + ex.getMessage());
    }

}
