package com.zrzk.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.zrzk.mapper.LoginMapper;
import com.zrzk.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public List login(){

        /*User user1 = loginMapper.selectOne(new Wrapper<User>() {
            @Override
            public User getEntity() {
                User user = new User();
                user.setUserName("tom");
                user.setPassWord("123");
                return user;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public String getSqlSegment() {
                return null;
            }
        });*/
        HashMap<String, Object> map = new HashMap<>();
        map.put("username","tom");
        map.put("password",123);
        List<User> users = loginMapper.selectByMap(map);
        System.out.println("users = " + users);
        return users;
    }
}
