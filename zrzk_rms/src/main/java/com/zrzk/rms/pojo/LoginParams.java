package com.zrzk.rms.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginParams implements Serializable {
    //账号
    private String userName;
    //密码
    private String passWord;
    //token
    private String tokenClean;
    //是否记住
    private Boolean rememberMe = false;

}
