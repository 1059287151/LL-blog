package com.ll.blog.controller;

import com.ll.blog.model.dto.FootprintUpdateDTO;
import com.ll.blog.model.vo.FootprintVO;
import com.ll.blog.result.Result;
import com.ll.blog.service.FootprintService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/footprints")
@Slf4j
@RequiredArgsConstructor
public class FootprintController {

    private final FootprintService footprintService;

    @GetMapping
    public Result<List<FootprintVO>> getFootprint(){
        log.info("获取当前足迹列表");
        return Result.success(footprintService.getCurrentFootprint());
    }

    @PostMapping
    public Result<Void> updateFootprint(@RequestBody FootprintUpdateDTO footprintUpdateDTO){
        log.info("保存或者跟新足迹:{}", footprintUpdateDTO);
        footprintService.updateFootprint(footprintUpdateDTO);
        return Result.success();
    }

    @DeleteMapping
    public Result<Void> deleteFootprint(@RequestParam Long id){
        log.info("删除足迹, id为:{}", id);
        footprintService.deleteById(id);
        return Result.success();
    }
}

