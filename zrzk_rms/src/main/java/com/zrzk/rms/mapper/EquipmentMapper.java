package com.zrzk.rms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrzk.rms.pojo.Equipment;
import com.zrzk.rms.pojo.QueryParams;

import java.util.List;

public interface EquipmentMapper extends BaseMapper<Equipment> {

    /*@Select(value = "SELECT id,alert,equipment_code,latitude,imei,employer,equipment_name,device_id,platform,equipment_type,longitude,STATUS,report_time " +
            "FROM (SELECT id,alert,equipment_code,latitude,imei,employer,equipment_name,device_id,platform,equipment_type,longitude,STATUS,report_time FROM equipment ORDER BY report_time DESC) a " +
            "GROUP BY equipment_code ")*/
    List<Equipment> findAllNew(QueryParams queryParams);
}
