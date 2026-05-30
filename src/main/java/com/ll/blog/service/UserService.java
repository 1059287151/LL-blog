package com.ll.blog.service;

import com.ll.blog.model.po.User;

public interface UserService {
    User findByUsername(String username);
}
