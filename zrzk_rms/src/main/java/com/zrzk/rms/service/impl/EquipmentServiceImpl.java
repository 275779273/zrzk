package com.zrzk.rms.service.impl;

import com.github.pagehelper.PageHelper;
import com.zrzk.rms.pojo.Equipment;
import com.zrzk.rms.mapper.EquipmentMapper;
import com.zrzk.rms.pojo.QueryParams;
import com.zrzk.rms.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    //查询所有设备最新的数据
    @Override
    public List<Equipment> findAll(QueryParams queryParams) {
        PageHelper.startPage(queryParams.getCurrent(), queryParams.getSize());
        int startIndex = (queryParams.getCurrent()-1)*queryParams.getSize();
        PageHelper.offsetPage(startIndex,queryParams.getSize());
        return equipmentMapper.findAllNew(queryParams);
    }

    //添加设备
    @Transactional
    @Override
    public Integer save(Equipment equipment) {
        return equipmentMapper.insert(equipment);
    }
}
