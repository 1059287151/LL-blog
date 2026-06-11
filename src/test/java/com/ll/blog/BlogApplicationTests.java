package com.ll.blog;

import com.ll.blog.mapper.FootprintMapper;
import com.ll.blog.model.po.Footprint;
import com.ll.blog.utils.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void testUUID(){
        TokenUtil tokenUtil = new TokenUtil();
        String token = tokenUtil.generatorToken();
        System.out.println(token);
    }

    @Test
    void testEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("123456");
        System.out.println(hash);
    }

}
