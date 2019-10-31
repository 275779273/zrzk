package com.zrzk.rms.apicontroller;

import com.zrzk.rms.pojo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

@Api(value = "省市区信息", tags = "省市区信息")
public interface ApiDistrictController {

    @ApiOperation(value = "获取所有省信息", notes = "没有参数", httpMethod = "GET")
    Result findAllProvinces();

    @ApiOperation(value = "根据id查询所有下属市/区/县信息", notes = "", httpMethod = "GET")
    @ApiImplicitParam(paramType = "path", name = "id", required = false, type = "int")
    Result findCityById(@PathVariable(value = "id") Integer id);
}
