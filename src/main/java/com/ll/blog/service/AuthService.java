package com.ll.blog.service;

import com.ll.blog.model.vo.LoginVO;

public interface AuthService {

    LoginVO login(String username, String password);

    void logout(String token);

}
