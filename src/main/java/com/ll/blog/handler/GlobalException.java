package com.ll.blog.handler;

import com.ll.blog.exception.BaseException;
import com.ll.blog.exception.BusinessException;
import com.ll.blog.result.Result;
import lombok.extern.slf4j.Slf4j;
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
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    public Result<Void> exceptionHandler(BusinessException ex){
        log.info("文章异常信息:{}",ex.getMessage());
        return Result.error(ex.getMessage());
    }

}
