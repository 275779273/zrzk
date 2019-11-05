package com.zrzk.rms.service;

import com.zrzk.rms.pojo.TUser;

import java.util.Map;

public interface UserService {
    TUser update(Map<String,String> map);

    Integer register(TUser user);

    TUser getUserByName(String getMapByName);
}
