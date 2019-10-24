package com.zrzk.rms.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("s_provinces")
public class District implements Serializable {
    //id(非主键)
    private Integer id;
    //名称
    private String cityName;
    //父id
    private Integer parentId;
    //简称
    private String shortName;
    //层级(1:省,2:市,3:区)
    private Integer depth;
    //长途区号
    private String cityCode;
    //邮编
    private String zipCode;
    //全称
    private String mergerName;
    //经度
    private String longitude;
    //纬度
    private String latitude;
    //拼音
    private String pinyin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMergerName() {
        return mergerName;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", parentId=" + parentId +
                ", shortName='" + shortName + '\'' +
                ", depth=" + depth +
                ", cityCode='" + cityCode + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", mergerName='" + mergerName + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
