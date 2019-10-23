package com.zrzk.rms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrzk.pojo.Equipment;
import com.zrzk.pojo.Result;
import com.zrzk.rms.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rms")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping("/findAll")
    public Result findAll(Long current,Long size){
        if(current==null||current<1){
            current=1L;
        }
        if(size==null||size<1){
            size=10L;
        }
        Page<Equipment> page = equipmentService.findAll(current,size);
        if(page==null||page.getRecords().size()<=0){
            return new Result(false,"未查询到数据");
        }else {
            return new Result(true,"查询到数据",page);
        }
    }
}
