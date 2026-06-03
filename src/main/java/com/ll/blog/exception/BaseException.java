package com.ll.blog.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {
    private final int code;

    public BaseException(String message) {
        super(message);
        this.code = 400;
    }

    public BaseException(String message, int code) {
        super(message);
        this.code = code;
    }
}
