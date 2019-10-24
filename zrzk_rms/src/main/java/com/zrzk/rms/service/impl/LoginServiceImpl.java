package com.zrzk.rms.service.impl;

import com.zrzk.rms.mapper.UserMapper;
import com.zrzk.rms.pojo.TUser;
import com.zrzk.rms.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据账号查询用户信息(角色/权限)
     * @param username
     * @return
     */
    @Override
    public TUser getUserByName(String username) {
        return userMapper.findUserByName(username);
    }
}