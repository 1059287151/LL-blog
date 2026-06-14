package com.ll.blog.controller;

import com.ll.blog.model.vo.AboutInfoVO;
import com.ll.blog.result.Result;
import com.ll.blog.service.AboutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/about")
@RequiredArgsConstructor
@Slf4j
public class AboutController {

    private final AboutService aboutService;

    @GetMapping
    public Result<AboutInfoVO> getAboutInfo() {
        log.info("获取关于信息");
        AboutInfoVO vo = aboutService.getTimeline();
        return Result.success(vo);
    }

}
