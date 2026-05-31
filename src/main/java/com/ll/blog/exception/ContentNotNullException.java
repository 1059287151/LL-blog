package com.ll.blog.exception;

public class ContentNotNullException extends BaseException{

    public ContentNotNullException(String message) {
        super(message);
    }

    public ContentNotNullException(String message, int code) {
        super(message, code);
    }
}
