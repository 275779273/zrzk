package com.zrzk.pojo;

import java.io.Serializable;

public class Equipment implements Serializable {
    //id
    private Integer id;

    //所在机构
    private String employer;

    //设备类别
    private String equipmentType;

    //编号
    private String equipmentCode;

    //设备名称
    private String equipmentName;

    //
    private String imei;

    //最后上报时间
    private String reportTime;

    //报警
    private String alert;

    //状态
    private String status;

    //上游平台
    private String platform;

    //DeviceID
    private String deviceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", employer='" + employer + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                ", equipmentCode='" + equipmentCode + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", imei='" + imei + '\'' +
                ", reportTime='" + reportTime + '\'' +
                ", alert='" + alert + '\'' +
                ", status='" + status + '\'' +
                ", platform='" + platform + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
