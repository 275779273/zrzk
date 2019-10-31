package com.zrzk.rms.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@ApiModel("user实体")
@TableName("t_user")
public class TUser implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //账号
    @ApiParam(value = "账号", required = true)
    @TableField(value = "username")
    private String userName;

    //密码
    @ApiParam(value = "密码", required = true)
    @TableField("password")
    private String passWord;

    //用户名
    @ApiParam(value = "用户名称")
    private String loginName;

    //注册时间
    @ApiParam(value = "注册时间")
    private Date registerTime;

    @TableField(exist = false)
    private List<TRole> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public List<TRole> getRoles() {
        return roles;
    }

    public void setRoles(List<TRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", loginName='" + loginName + '\'' +
                ", registerTime=" + registerTime +
                ", roles=" + roles +
                '}';
    }
}

