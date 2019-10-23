package com.zrzk.pojo;


import com.zrzk.utils.DateUtil;

import java.util.Date;

//井盖
public class Cover {
    //主键
    private Integer id;

    private Integer genreId = 0;

    private String genreName = "0";
    //数值
    private Double number = 0.0;

    //单位
    private String unitName = "0";
    //状态(0:异常,1:正常)
    private Integer status = 0;

    private String statusStr;
    //描述
    private String title = "0";
    //图片地址
    private String imgUrl = "0";

    //井盖编号
    private String equipmentCode = "0";

    //插入时间
    private Date insertTime;
    private String insertTimeStr;

    //温度
    private Double temperature = 0.0;

    //测量值
    private Double measuredNumber = 0.0;

    //压力
    private Double stress = 0.0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        if (status == 0) {
            statusStr = "正常";
        } else if (status == 1) {
            statusStr = "异常";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getInsertTimeStr() {
        if(insertTime==null){
            return "";
        }else {
            return DateUtil.dateToString(insertTime, "yyyy-MM-dd HH:mm:ss");
        }
    }

    public void setInsertTimeStr(String insertTimeStr) {
        this.insertTimeStr = insertTimeStr;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getMeasuredNumber() {
        return measuredNumber;
    }

    public void setMeasuredNumber(Double measuredNumber) {
        this.measuredNumber = measuredNumber;
    }

    public Double getStress() {
        return stress;
    }

    public void setStress(Double stress) {
        this.stress = stress;
    }

    @Override
    public String toString() {
        return "Cover{" +
                "id=" + id +
                ", genreId=" + genreId +
                ", genreName='" + genreName + '\'' +
                ", number=" + number +
                ", unitName='" + unitName + '\'' +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", equipmentCode='" + equipmentCode + '\'' +
                ", insertTime=" + insertTime +
                ", insertTimeStr='" + insertTimeStr + '\'' +
                ", temperature=" + temperature +
                ", measuredNumber=" + measuredNumber +
                ", stress=" + stress +
                '}';
    }
}
