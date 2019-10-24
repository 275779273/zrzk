package com.zrzk.rms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrzk.rms.mapper.UserMapper;
import com.zrzk.rms.pojo.TUser;
import com.zrzk.rms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public Integer update(String userName, String oldPassWord, String newPassWord) {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        TUser tUser = new TUser();
        tUser.setUserName(userName);
        tUser.setPassWord(oldPassWord);
        wrapper.setEntity(tUser);
        //查询是否存在此用户
        TUser tUser1 = userMapper.selectOne(wrapper);
        if(tUser1!=null){   //存在
            tUser.setPassWord(newPassWord);
            return userMapper.update(tUser, null);
        }else {     //不存在
            return null;
        }
    }
}
