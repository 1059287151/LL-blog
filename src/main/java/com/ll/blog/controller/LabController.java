package com.ll.blog.controller;

import com.ll.blog.model.vo.LabRandomVO;
import com.ll.blog.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/lab")
public class LabController {

    private static final List<String> TOOLS = List.of(
            "fluid-particles",
            "pixel-painter",
            "life-progress",
            "white-noise",
            "tarot"
    );

    @GetMapping("/random")
    public Result<LabRandomVO> getLabRandom(){
        String randomName = TOOLS.get(new Random().nextInt(TOOLS.size()));
        LabRandomVO vo = new LabRandomVO(randomName, new HashMap<>());
        return Result.success(vo);
    }

}
