package com.zrzk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrzk.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<User> {
}
