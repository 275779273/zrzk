package com.zrzk.rms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zrzk.rms.pojo.Equipment;
import com.zrzk.rms.pojo.QueryParams;
import com.zrzk.rms.pojo.Result;
import com.zrzk.rms.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping("/findAll")
    public Result findAll(String paramsJson){
        JSONObject jsonObject = JSON.parseObject(paramsJson);
        QueryParams params = JSON.toJavaObject(jsonObject, QueryParams.class);
        if(params.getCurrent()==null||params.getCurrent()<1){
            params.setCurrent(1);
        }
        if(params.getSize()==null||params.getSize()<1){
            params.setSize(10);
        }

        List<Equipment> equipmentList = equipmentService.findAll(params);
        if(equipmentList==null||equipmentList.size()<=0){
            return new Result(false,"未查询到数据");
        }else {
            PageInfo<Equipment> info = new PageInfo<>(equipmentList);
            return new Result(true,"查询到数据",info);
        }
    }
}
