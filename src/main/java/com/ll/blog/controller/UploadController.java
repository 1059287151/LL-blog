package com.ll.blog.controller;

import com.ll.blog.config.AliyunOSSOperator;
import com.ll.blog.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UploadController {

    private final AliyunOSSOperator aliyunOSSOperator;

    public Result<String> upload(MultipartFile file) {
        log.info("文件上传{}",file);
        String url = null;
        try {
            url = aliyunOSSOperator.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("文件上传OSS，url{}",url);
        return Result.success(url);
    }

}
