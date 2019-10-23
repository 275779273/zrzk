package com.zrzk.pojo;

import java.util.Set;

public class TUser {
    private Integer id;

    private String username;

    private String password;

    private String loginname;

    /**
     * 用户对应的角色集合
     */
    private Set<TRole> roles;

    public TUser(Integer id, String username, String password, String loginname, Set<TRole> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.loginname = loginname;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public Set<TRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<TRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loginname='" + loginname + '\'' +
                ", roles=" + roles +
                '}';
    }
}