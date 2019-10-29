package com.zrzk.rms.controller;

import com.alibaba.fastjson.JSONObject;
import com.zrzk.rms.pojo.Result;
import com.zrzk.rms.service.LoginService;
import com.zrzk.rms.service.UserService;
import com.zrzk.rms.tokenUtils.TokenProccessor;
import com.zrzk.rms.tokenUtils.TokenTools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public JSONObject login(String username, String password,String tokenClean, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();

        Object token1 = request.getSession().getAttribute("token");
        String token2 = "";
        if(token1!=null){
            token2= (String) token1;
        }

        if(tokenClean!=null&&token1!=null&&!"".equals(tokenClean)&&!"".equals(token2)&&tokenClean.equals(token2)){
            jsonObject.put("msg","登录成功");
            jsonObject.put("success",true);
            return jsonObject;
        }

        //测试当前用户是否已经认证
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            //
            //token.setRememberMe(true);
            try {
                //进行验证，这里可以捕获异常，然后返回对应信息
                subject.login(token);
                System.out.println("----------->"+subject.getSession().getId());

                String tokenService = (String) subject.getSession().getId();
                System.out.println("tokenService = " + tokenService);
                //存入token
                jsonObject.put("token",tokenService);

                request.getSession().setAttribute("token",tokenService);

                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                if(savedRequest!=null){
                    String url = savedRequest.getRequestUrl();      //获取原路径
                    //return new Result(true,url,jsonObject);
                    jsonObject.put("msg", "登录成功");
                    jsonObject.put("success",true);
                }
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
            } catch (IncorrectCredentialsException e) {
                jsonObject.put("msg", "密码错误");
            } catch (LockedAccountException e) {
                jsonObject.put("msg", "登录失败，该用户已被冻结");
            } catch (AuthenticationException e) {
                jsonObject.put("msg", "该用户不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //return new Result(true,"/");
        return jsonObject;
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


    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    public Object unauth() {
        Map<String, Object> map = new HashMap();
        map.put("success", false);
        map.put("msg", "未登录");
        return map;
    }
}
