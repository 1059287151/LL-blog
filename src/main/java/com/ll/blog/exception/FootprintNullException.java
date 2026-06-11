package com.ll.blog.exception;

public class FootprintNullException extends BaseException{
    public FootprintNullException(String message) {
        super(message);
    }

    public FootprintNullException(String message, int code) {
        super(message, code);
    }
}
