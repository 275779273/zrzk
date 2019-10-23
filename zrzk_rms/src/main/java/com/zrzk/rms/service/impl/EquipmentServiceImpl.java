package com.zrzk.rms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrzk.pojo.Equipment;
import com.zrzk.rms.mapper.EquipmentMapper;
import com.zrzk.rms.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    //查询每台设备最新的数据
    @Override
    public Page<Equipment> findAll(long current,long size) {
        Page<Equipment> page = new Page<>(1,5);
        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
        //排序加分组
        wrapper.orderByDesc("report_time").groupBy("equipment_code");
        IPage<Equipment> equipmentIPage = equipmentMapper.selectPage(page,wrapper);
        return (Page<Equipment>) equipmentIPage;
    }
}
