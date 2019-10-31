package com.zrzk.rms.apicontroller;

import com.zrzk.rms.pojo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "设备信息",tags = "设备信息")
public interface ApiEquipmentController {

    @ApiOperation(value = "获取所有设备信息", notes = "将 current,size,employer,equipmentType,equipmentCode,equipmentName,platform 组合为Json类型参数",httpMethod = "POST")
    @ApiImplicitParam(paramType = "body", name = "params", value = "组合参数", required = true)
    Result findAll(@RequestBody(required = false) String params);
}
