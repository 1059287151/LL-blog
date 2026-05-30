package com.ll.blog.exception;

import lombok.Getter;

@Getter
public class BusinessException extends BaseException{
    private final int code;

    public BusinessException(String message) {
        super(message);
        this.code = 400;
    }

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

}
