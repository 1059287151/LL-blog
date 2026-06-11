package com.ll.blog.exception;

public class FriendLinkNullException extends BaseException{
    public FriendLinkNullException(String message) {
        super(message);
    }

    public FriendLinkNullException(String message, int code) {
        super(message, code);
    }
}
