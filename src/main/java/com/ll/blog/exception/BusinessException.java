package com.ll.blog.exception;

import lombok.Getter;

@Getter
public class BusinessException extends BaseException{


    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, int code) {
        super(message, code);
    }
}
