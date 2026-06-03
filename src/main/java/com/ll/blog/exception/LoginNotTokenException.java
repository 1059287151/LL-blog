package com.ll.blog.exception;

public class LoginNotTokenException extends BaseException {
    public LoginNotTokenException(String message) {
        super(message);
    }

    public LoginNotTokenException(String message, int code) {
        super(message, code);
    }
}
