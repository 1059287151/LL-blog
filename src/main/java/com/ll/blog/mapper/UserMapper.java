package com.ll.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ll.blog.model.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
