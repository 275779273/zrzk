package com.zrzk.rms.controller;

import com.zrzk.rms.pojo.Result;
import com.zrzk.rms.service.LoginService;
import com.zrzk.rms.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public Result login(String username, String password, HttpServletRequest request){
        HashMap<String, String> map = new HashMap<>();

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        //测试当前用户是否已经认证
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            //
            //token.setRememberMe(true);
            try {
                //进行验证，这里可以捕获异常，然后返回对应信息
                subject.login(token);
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                if(savedRequest!=null){
                    String url = savedRequest.getRequestUrl();      //获取原路径
                    map.put("username",username);
                    map.put("password",password);
                    return new Result(true,url,map);
                }
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return new Result(false,"账号或密码错误！");
            } catch (AuthorizationException e) {
                e.printStackTrace();
                return new Result(false,"没有权限");
            }
        }
        return new Result(true,"/");
    }

    @RequestMapping
    public Result add(){
        return null;
    }

    @RequestMapping("/update")
    public Result update(String userName,String oldPassWord,String newPassWord){
        Integer update = userService.update(userName, oldPassWord, newPassWord);
        if(update==null||update<=0){
            return new Result(false,"修改失败");
        }else {
            return new Result(true,"修改成功");
        }
    }
}
