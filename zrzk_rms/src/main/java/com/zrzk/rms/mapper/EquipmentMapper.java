package com.zrzk.rms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrzk.rms.pojo.Equipment;
import com.zrzk.rms.pojo.QueryParams;

import java.util.List;

public interface EquipmentMapper extends BaseMapper<Equipment> {

    List<Equipment> findAllNew(QueryParams queryParams);
}
