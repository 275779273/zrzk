package com.zrzk.dao;

import com.zrzk.pojo.Device;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DeviceDao {

    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "column01",column = "column_01"),
            @Result(property = "column02",column = "column_02"),
            @Result(property = "column03",column = "column_03"),
            @Result(property = "column04",column = "column_04"),
            @Result(property = "timeStr",column = "column_05"),
            //@Result(property = "column05",column = "column_05"),
            @Result(property = "column06",column = "column_06"),
            @Result(property = "column07",column = "column_07"),
            @Result(property = "column08",column = "column_08"),
            @Result(property = "column09",column = "column_09"),
            @Result(property = "column10",column = "column_10"),
            @Result(property = "column11",column = "column_11"),
            @Result(property = "column12",column = "column_12"),
            @Result(property = "column13",column = "column_13"),
            @Result(property = "column14",column = "column_14"),
            @Result(property = "column15",column = "column_15"),
    })
    @Insert(value = "INSERT INTO device_log (column_01,column_02,column_03,column_04,column_05,column_06,column_07,column_08,column_09,column_10,column_11,column_12,column_13,column_14,column_15)\n" +
            "VALUES(#{column01},#{column02},#{column03},#{column04},#{timeStr},#{column06},#{column07},#{column08},#{column09},#{column10},#{column11},#{column12},#{column13},#{column14},#{column15});")
    Integer save(Device device);

    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "column01",column = "column_01"),
            @Result(property = "column02",column = "column_02"),
            @Result(property = "column03",column = "column_03"),
            @Result(property = "column04",column = "column_04"),
            @Result(property = "timeStr",column = "column_05"),
            //@Result(property = "column05",column = "column_05"),
            @Result(property = "column06",column = "column_06"),
            @Result(property = "column07",column = "column_07"),
            @Result(property = "column08",column = "column_08"),
            @Result(property = "column09",column = "column_09"),
            @Result(property = "column10",column = "column_10"),
            @Result(property = "column11",column = "column_11")

    })
    @Insert(value = "INSERT INTO fluviograph_log (column_01,column_02,column_03,column_04,column_05,column_06,column_07,column_08,column_09,column_10,column_11)\n" +
            "VALUES(#{column01},#{column02},#{column03},#{column04},#{timeStr},#{column06},#{column07},#{column08},#{column09},#{column10},#{column11});")
    Integer save1(Device device);
}
