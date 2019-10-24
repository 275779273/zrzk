package com.zrzk.rms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrzk.rms.pojo.TUser;


public interface UserMapper extends BaseMapper<TUser> {

    TUser findUserByName(String username);
}
