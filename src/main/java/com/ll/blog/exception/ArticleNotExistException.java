package com.ll.blog.exception;

public class ArticleNotExistException extends BaseException {
    public ArticleNotExistException(String message) {
        super(message);
    }


    public ArticleNotExistException(String message, int code) {
        super(message, code);
    }
}
