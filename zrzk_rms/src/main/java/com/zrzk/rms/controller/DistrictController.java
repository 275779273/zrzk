package com.zrzk.rms.controller;

import com.zrzk.rms.pojo.Result;
import com.zrzk.rms.pojo.District;
import com.zrzk.rms.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService cityService;

    /**
     * 获取所有省的数据
     * @return
     */
    @RequestMapping("/provinces")
    public Result findAllProvinces(){
        List<District> cityList = cityService.findAllProvinces();
        if(cityList==null||cityList.size()<=0){
            return new Result(false,"为查询到数据");
        }else {
            return new Result(true,"查询到数据",cityList);
        }
    }

    @RequestMapping("/city/{id}")
    public Result findCityById(@PathVariable(value = "id",required = true) Integer id){
        List<District> cityList = cityService.findCityById(id);
        if(cityList==null||cityList.size()<=0){
            return new Result(false,"为查询到数据");
        }else {
            return new Result(true,"查询到数据",cityList);
        }
    }
}
