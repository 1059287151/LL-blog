package com.ll.blog.exception;

public class AnimeNotExist extends BaseException {
    public AnimeNotExist(String message) {
        super(message);
    }

    public AnimeNotExist(String message, int code) {
        super(message, code);
    }
}
