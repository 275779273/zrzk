package com.zrzk_mybatisplus;

import com.zrzk_mybatisplus.pojo.TUser;
import com.zrzk_mybatisplus.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class demo {



    public static void main(String[] args) {
        BaseService baseService = new BaseService();
        TUser tUser = new TUser();
        tUser.setUsername("xiaoming");
        tUser.setPassward("123");
        tUser.setLoginname("小明");
        int insert = baseService.insert(tUser);
        System.out.println("insert = " + insert);
    }
}
