package com.zrzk.controller;

import com.zrzk.mapper.LoginMapper;
import com.zrzk.pojo.Result;
import com.zrzk.pojo.User;
import com.zrzk.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zrzk")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public Result login(){
        List login = loginService.login();
        if(login==null||login.size()<=0){
            return new Result(false,"失败");
        }else {
            return new Result(true,"成功");
        }

    }
}
