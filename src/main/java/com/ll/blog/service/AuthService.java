package com.ll.blog.service;

import com.ll.blog.model.dto.RegisterDTO;
import com.ll.blog.model.vo.LoginVO;

public interface AuthService {

    LoginVO login(String username, String password);

    LoginVO.UserVO register(RegisterDTO dto);

    void logout(String token);

}
