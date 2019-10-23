package com.zrzk.service.impl;



import com.zrzk.pojo.TPermission;
import com.zrzk.pojo.TRole;
import com.zrzk.pojo.TUser;
import com.zrzk.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {

    public TUser getUserByName(String getMapByName) {
        //模拟数据库查询，正常情况此处是从数据库或者缓存查询。
        return getMapByName(getMapByName);
    }

    /**
     * 模拟数据库查询
     * @param userName
     * @return
     */
    private TUser getMapByName(String userName){
        //共添加两个用户，两个用户都是admin一个角色，
        //wsl有query和add权限，zhangsan只有一个query权限
        TPermission permissions1 = new TPermission(1,"query");
        TPermission permissions2 = new TPermission(2,"add");
        Set<TPermission> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);
        TRole role = new TRole(1,"admin",permissionsSet);
        Set<TRole> roleSet = new HashSet<>();
        roleSet.add(role);
        TUser user = new TUser(1,"xiaoming","48e231e66ff8943db0f6d2b6cb6536d2","小明",roleSet);
        Map<String ,TUser> map = new HashMap<>();
        map.put(user.getUsername(), user);

        TPermission permissions3 = new TPermission(3,"query");
        Set<TPermission> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions3);
        TRole role1 = new TRole(2,"user",permissionsSet1);
        Set<TRole> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        TUser user1 = new TUser(2,"zhangsan","123456","张三",roleSet1);
        map.put(user1.getUsername(), user1);
        return map.get(userName);
    }
}