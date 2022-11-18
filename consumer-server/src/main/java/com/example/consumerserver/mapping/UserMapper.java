package com.example.consumerserver.mapping;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.consumerserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
