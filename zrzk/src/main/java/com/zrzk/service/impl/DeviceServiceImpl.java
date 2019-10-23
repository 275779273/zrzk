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
            return deviceDao.save1(device);
        }
        System.out.println(device.getTimeStr());
        return null;
    }
}
