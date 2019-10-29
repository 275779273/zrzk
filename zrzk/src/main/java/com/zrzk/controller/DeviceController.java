package com.zrzk.controller;

import com.alibaba.fastjson.JSONObject;
import com.zrzk.pojo.Device;
import com.zrzk.pojo.Result;
import com.zrzk.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//获取井盖数据
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/save")
    public Result save(@RequestBody String body){
        System.out.println("body = " + body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        Device device = JSONObject.toJavaObject(jsonObject, Device.class);
        Integer integer = deviceService.save(device);
        if(integer>0){
            return new Result(true,"添加成功");
        }else {
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/get")
    public String get(@RequestBody String getBody){
        System.out.println("getBody = " + getBody);
        return "访问成功";
    }
}
