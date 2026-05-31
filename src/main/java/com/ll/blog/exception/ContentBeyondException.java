package com.ll.blog.exception;

public class ContentBeyondException extends BaseException{
    public ContentBeyondException(String message) {
        super(message);
    }

    public ContentBeyondException(String message, int code) {
        super(message, code);
    }
}
