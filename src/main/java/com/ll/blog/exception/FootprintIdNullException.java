package com.ll.blog.exception;

public class FootprintIdNullException extends BaseException {

    public FootprintIdNullException(String message) {
        super(message);
    }

    public FootprintIdNullException(String message, int code) {
        super(message, code);
    }
}
