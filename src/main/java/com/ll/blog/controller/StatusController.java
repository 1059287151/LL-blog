package com.ll.blog.controller;

import com.ll.blog.model.dto.UserDTO;
import com.ll.blog.model.vo.StatusNowVO;
import com.ll.blog.result.Result;
import com.ll.blog.service.StatusService;
import com.ll.blog.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
@Slf4j
@RequiredArgsConstructor
public class StatusController {
    // TODO 此接口需要登录后才有用
    private final StatusService statusService;

    @GetMapping("/now")
    public Result<StatusNowVO> getStatusNow(){
        log.info("获取当前状态");
        UserDTO user = UserHolder.getUser();
        if (user == null) {
            return Result.error("请先登录");
        }
        Long userId = user.getId();
        StatusNowVO vo = statusService.getStatusNow(userId);
        return Result.success(vo);
    }

}
