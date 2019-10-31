package com.zrzk.rms.controller;

import com.zrzk.rms.apicontroller.ApiDistrictController;
import com.zrzk.rms.pojo.Result;
import com.zrzk.rms.pojo.District;
import com.zrzk.rms.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/district")
//解决跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
public class DistrictController implements ApiDistrictController {

    @Autowired
    private DistrictService cityService;

    /**
     * 获取所有省的数据
     *
     * @return
     */
    @RequestMapping(value = "/provinces", method = RequestMethod.GET)
    public Result findAllProvinces() {
        List<District> cityList = cityService.findAllProvinces();
        if (cityList == null || cityList.size() <= 0) {
            return new Result(false, "为查询到数据");
        } else {
            return new Result(true, "查询到数据", cityList);
        }
    }

    @RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
    public Result findCityById(@PathVariable(value = "id", required = true) Integer id) {
        List<District> cityList = cityService.findCityById(id);
        if (cityList == null || cityList.size() <= 0) {
            return new Result(false, "为查询到数据");
        } else {
            return new Result(true, "查询到数据", cityList);
        }
    }
}
