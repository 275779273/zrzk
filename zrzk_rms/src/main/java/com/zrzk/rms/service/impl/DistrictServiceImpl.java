package com.zrzk.rms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrzk.rms.pojo.District;
import com.zrzk.rms.mapper.DistrictMapper;
import com.zrzk.rms.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    /**
     * 查询所有省份
     * @return
     */
    @Override
    public List<District> findAllProvinces() {
        QueryWrapper<District> wrapper = new QueryWrapper<>();
        wrapper.eq("depth",1);
        return districtMapper.selectList(wrapper);
    }

    /**
     * 根据parentId查询下属市,区/县
     * @param id
     * @return
     */
    @Override
    public List<District> findCityById(Integer id) {
        QueryWrapper<District> wrapper = new QueryWrapper<>();
        wrapper.eq("parentId",id);
        return districtMapper.selectList(wrapper);
    }
}
