package com.zrzk.rms.service;

import com.zrzk.rms.pojo.District;

import java.util.List;

public interface DistrictService {

    List<District> findAllProvinces();

    List<District> findCityById(Integer id);
}
