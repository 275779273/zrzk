package com.zrzk.rms.pojo;

import lombok.Data;

import java.io.Serializable;

//查询参数实体类
@Data
public class QueryParams implements Serializable {

    //当前页码
    private Integer current;
    //每页条数
    private Integer size;

    //选择机构
    private String employer;
    //设备类别
    private String equipmentType;
    //设备编号
    private String equipmentCode;
    //设备名称
    private String equipmentName;
    //运营商
    private String platform;

}
