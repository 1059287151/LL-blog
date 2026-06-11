package com.ll.blog.controller;

import com.ll.blog.model.dto.StatusNowDTO;
import com.ll.blog.model.dto.UserDTO;
import com.ll.blog.model.vo.StatusNowVO;
import com.ll.blog.result.Result;
import com.ll.blog.service.StatusService;
import com.ll.blog.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
@Slf4j
@RequiredArgsConstructor
public class StatusController {
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

    @PutMapping("/now")
    public Result<Void> updateStatusNow(@RequestBody StatusNowDTO dto){
        log.info("跟新个人状态:{}", dto);
        UserDTO user = UserHolder.getUser();
        if (user == null) {
            return Result.error("请先登录");
        }
        statusService.updateStatusNow(user.getId(), dto);
        return Result.success();
    }
}
