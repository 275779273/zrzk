package com.zrzk.rms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrzk.pojo.Equipment;


public interface EquipmentService {
    Page<Equipment> findAll(long current,long size);
}
