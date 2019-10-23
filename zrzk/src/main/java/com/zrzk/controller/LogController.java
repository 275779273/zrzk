package com.zrzk.controller;

import com.zrzk.pojo.Result;
import com.zrzk.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/save")
    public Result save(String logStr){
        Integer integer = logService.save(logStr);
        if(integer>0){
            return new Result(true,"添加数据成功");
        }else {
            return new Result(false,"添加数据失败");
        }
    }
}
