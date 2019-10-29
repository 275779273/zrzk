package com.zrzk.rms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zrzk.rms.pojo.Equipment;
import com.zrzk.rms.pojo.QueryParams;
import com.zrzk.rms.pojo.Result;
import com.zrzk.rms.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/equipment")
@CrossOrigin(origins = "*",maxAge = 3600)
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping("/findAll")
    public Result findAll(@RequestBody(required = false)String data){
        JSONObject jsonObject = JSON.parseObject(data);
        QueryParams params = JSON.toJavaObject(jsonObject, QueryParams.class);
        if(params==null){
            params = new QueryParams();
            params.setCurrent(1);
            params.setSize(10);
        }else {
            if(params.getCurrent()==null||params.getCurrent()<1){
                params.setCurrent(1);
            }
            if(params.getSize()==null||params.getSize()<1){
                params.setSize(10);
            }
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
