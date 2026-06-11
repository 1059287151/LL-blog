package com.ll.blog.service;

import com.ll.blog.model.dto.StatusNowDTO;
import com.ll.blog.model.vo.StatusNowVO;

public interface StatusService {
    StatusNowVO getStatusNow(Long userId);

    void updateStatusNow(Long userId, StatusNowDTO dto);
}
