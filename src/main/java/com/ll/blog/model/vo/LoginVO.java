package com.ll.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {
    private String token;
    private UserVO user;
    @Data
    @AllArgsConstructor
    public static class UserVO {
        private Long id;
        private String username;
        private String nickname;
        private String avatar;
    }

}
