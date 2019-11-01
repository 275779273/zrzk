package com.zrzk.rms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zrzk.rms.apicontroller.ApiUserController;
import com.zrzk.rms.pojo.LoginParams;
import com.zrzk.rms.pojo.Result;
import com.zrzk.rms.pojo.TUser;
import com.zrzk.rms.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController implements ApiUserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户登录
     * @param params
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody String params) {
        JSONObject jsonObject1 = JSON.parseObject(params);
        LoginParams loginParams = jsonObject1.toJavaObject(LoginParams.class);

        String username = loginParams.getUserName();
        String password = loginParams.getPassWord();
        //String tokenClean = loginParams.getTokenClean();
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();

        //测试当前用户是否已经认证
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, loginParams.getRememberMe());  //设置是否记住

            try {
                //进行验证，这里可以捕获异常，然后返回对应信息
                subject.login(token);

                //获取token
                String tokenService = (String) subject.getSession().getId();
                TUser user = userService.getUserByName(username);
                //token存入session
                //request.getSession().setAttribute("token", tokenService);
                logger.info("登录成功");
                return new Result(true, "登录成功", user, tokenService);
               /* SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                if (savedRequest != null) {
                    String url = savedRequest.getRequestUrl();      //获取原路径
                    return new Result(true,"登录成功",null,tokenService);
                }*/
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
            } catch (IncorrectCredentialsException e) {
                logger.error(e.getMessage());
                return new Result(false, "密码错误");
            } catch (LockedAccountException e) {
                logger.error(e.getMessage());
                return new Result(false, "登录失败，该用户已被冻结");
            } catch (AuthenticationException e) {
                logger.error(e.getMessage());
                return new Result(false, "该用户不存在");
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return new Result(true, "已认证");
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody TUser user) {
        Integer integer = userService.register(user);
        if (integer != null && integer > 0) {
            return new Result(true, "注册成功");
        } else {
            return new Result(false, "注册失败");
        }
    }

    /**
     * 修改用户信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody String params) {        //String userName, String oldPassWord, String newPassWord, String loginName
        Map<String, String> map = JSON.parseObject(params, HashMap.class);
        Integer update = userService.update(map);
        if (update == null || update <= 0) {
            return new Result(false, "修改失败");
        } else {
            return new Result(true, "修改成功");
        }
    }

    /**
     * 用户登出
     * @return
     */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/logout";
    }

}
