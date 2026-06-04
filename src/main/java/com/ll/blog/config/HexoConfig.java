package com.ll.blog.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "hexo")
public class HexoConfig {
    // posts 目录，默认 classpath:hexo/source/_posts/
    private String postsPath = "classpath:hexo/source/_posts/";
}
