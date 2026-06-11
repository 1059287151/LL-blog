package com.ll.blog.service;

import com.ll.blog.model.dto.FootprintUpdateDTO;
import com.ll.blog.model.vo.FootprintVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FootprintService {

    List<FootprintVO> getCurrentFootprint();

    void updateFootprint(FootprintUpdateDTO footprintUpdateDTO);

    void deleteById(Long id);
}
