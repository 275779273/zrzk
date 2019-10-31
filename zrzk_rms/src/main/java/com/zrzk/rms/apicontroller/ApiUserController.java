package com.zrzk.rms.apicontroller;

import com.zrzk.rms.pojo.Result;
import com.zrzk.rms.pojo.TUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@Api(value = "用户信息",tags = "用户信息")
public interface ApiUserController {

    @ApiOperation(value = "登录方法", notes = "将 userName,passWord 组合为Json类型参数")
    @ApiImplicitParam(paramType = "body", name = "params", value = "组合参数", required = true)
    Result login(@RequestBody String params);

    @ApiOperation(value = "添加用户信息方法" , notes = "将 userName,passWord,loginName 组合为Json类型参数")
    @ApiImplicitParam(paramType = "body", name = "user", required = true)
    Result register(@RequestBody TUser user);

    @ApiOperation(value = "修改用户信息方法" , notes = "将 userName,oldPassWord,newPassWord,loginName 组合为Json类型参数")
    @ApiImplicitParam(paramType = "body", name = "user", required = true)
    Result update(@RequestBody String params);

    @ApiOperation(value = "登出方法" , notes = "没有参数")
    String logout();
}
