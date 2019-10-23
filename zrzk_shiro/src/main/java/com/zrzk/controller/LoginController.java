package com.zrzk.controller;

import com.zrzk.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shiro")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String login(String username,String password){
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        //测试当前用户是否已经认证
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try {
                //进行验证，这里可以捕获异常，然后返回对应信息
                subject.login(token);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return "账号或密码错误！";
            } catch (AuthorizationException e) {
                e.printStackTrace();
                return "没有权限";
            }

        }
        return "login success";
    }
}
