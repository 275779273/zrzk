package com.zrzk.service.impl;

import com.zrzk.dao.DeviceDao;
import com.zrzk.pojo.Device;
import com.zrzk.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDao deviceDao;

    @Override
    public Integer save(Device device) {
        if (device.getColumn01().equals("130")){
            return deviceDao.save(device);
        }
        if(device.getColumn01().equals("20")){
            //液位值单位转换 /1000
            String column07 = device.getColumn07();
            String column007 = Double.parseDouble(column07) /1000+"";
            device.setColumn07(column007);
            //温度值单位转换 /10
            String column08 = device.getColumn08();
            String column008 = Double.parseDouble(column08) / 10 + "";
            device.setColumn08(column008);
            return deviceDao.save1(device);
        }
        System.out.println(device.getTimeStr());
        return null;
    }
}
