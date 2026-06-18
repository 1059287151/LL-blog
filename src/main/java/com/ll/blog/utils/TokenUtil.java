package com.ll.blog.utils;

import cn.hutool.core.lang.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    /**
     * 生成的随机token
     * @return
     */
    public String generatorToken(){
        return UUID.randomUUID().toString(true);
    }
}
