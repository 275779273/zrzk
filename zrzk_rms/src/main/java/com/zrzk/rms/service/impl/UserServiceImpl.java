package com.zrzk.rms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrzk.rms.mapper.UserMapper;
import com.zrzk.rms.pojo.TUser;
import com.zrzk.rms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public TUser update(Map<String, String> map) {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        TUser tUser = new TUser();
        tUser.setUserName(map.get("userName"));
        tUser.setPassWord(map.get("oldPassWord"));
        wrapper.setEntity(tUser);
        //查询是否存在此用户
        TUser tUser1 = userMapper.selectOne(wrapper);
        if (tUser1 != null) {   //存在
            QueryWrapper<TUser> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("id", tUser1.getId());
            tUser.setPassWord(map.get("newPassWord"));
            tUser.setLoginName(map.get("loginName"));
            int integer = userMapper.update(tUser, wrapper1);
            if (integer > 0) {  //修改成功
                return tUser;
            }
        }
        //不存在
        return null;
    }

    @Override
    @Transactional
    public Integer register(TUser user) {
        return userMapper.insert(user);
    }

    /**
     * 根据账号查询用户信息(角色/权限)
     *
     * @param username
     * @return
     */
    @Override
    public TUser getUserByName(String username) {
        return userMapper.findUserByName(username);
    }
}
