package com.zrzk.shiro;

import com.zrzk.pojo.TPermission;
import com.zrzk.pojo.TRole;
import com.zrzk.pojo.TUser;
import com.zrzk.service.impl.LoginServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private HashedCredentialsMatcher hashedCredentialsMatcher;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("doGetAuthorizationInfo------------");
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名去数据库查询用户信息
        TUser user = loginService.getUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (TRole role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            //添加权限
            for (TPermission permission : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //设置加密方式
        this.setCredentialsMatcher(hashedCredentialsMatcher);
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        //String name = authenticationToken.getPrincipal().toString();
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();

        //查询数据库
        TUser user = loginService.getUserByName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            ByteSource bytes = ByteSource.Util.bytes(name);
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            //name : 登录的账号或者数据库查询到的账号 , user.getPassword().toString() : 数据库查询到的密码 , bytes : 加盐 , bytes, getName() : 调用父类的getName()方法
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name, user.getPassword().toString(),bytes, getName());
            return info;
        }
    }
}
