package com.zrzk.rms.service;

import com.zrzk.rms.pojo.Equipment;
import com.zrzk.rms.pojo.QueryParams;

import java.util.List;


public interface EquipmentService {
    List<Equipment> findAll(QueryParams queryParams);

    Integer save(Equipment equipment);
}
