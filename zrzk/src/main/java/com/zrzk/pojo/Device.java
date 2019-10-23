package com.zrzk.pojo;

//接收井盖数据
import com.zrzk.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class Device implements Serializable {
    private Integer id;
    private String column01;
    private String column02;
    private String column03;
    private String column04;
    //插入时间
    private String column05;
    //时间
    private Date timeStr;

    private String column06;
    private String column07;
    private String column08;
    private String column09;
    private String column10;
    private String column11;
    private String column12;
    private String column13;
    private String column14;
    private String column15;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColumn01() {
        return column01;
    }

    public void setColumn01(String column01) {
        this.column01 = column01;
    }

    public String getColumn02() {
        return column02;
    }

    public void setColumn02(String column02) {
        this.column02 = column02;
    }

    public String getColumn03() {
        return column03;
    }

    public void setColumn03(String column03) {
        this.column03 = column03;
    }

    public String getColumn04() {
        return column04;
    }

    public void setColumn04(String column04) {
        this.column04 = column04;
    }

    public String getColumn05() {
        return column05;
    }

    public void setColumn05(String column05) {
        this.column05 = column05;
    }

    public Date getTimeStr() {
        if(column05!=null&&!"".equals(column05)){
            return DateUtil.stringToDate(column05, "yyMMddHHmm");
        }else {
            return new Date();
        }
    }

    public void setTimeStr(Date timeStr) {
        this.timeStr = timeStr;
    }

    public String getColumn06() {
        return column06;
    }

    public void setColumn06(String column06) {
        this.column06 = column06;
    }

    public String getColumn07() {
        return column07;
    }

    public void setColumn07(String column07) {
        this.column07 = column07;
    }

    public String getColumn08() {
        return column08;
    }

    public void setColumn08(String column08) {
        this.column08 = column08;
    }

    public String getColumn09() {
        return column09;
    }

    public void setColumn09(String column09) {
        this.column09 = column09;
    }

    public String getColumn10() {
        return column10;
    }

    public void setColumn10(String column10) {
        this.column10 = column10;
    }

    public String getColumn11() {
        return column11;
    }

    public void setColumn11(String column11) {
        this.column11 = column11;
    }

    public String getColumn12() {
        return column12;
    }

    public void setColumn12(String column12) {
        this.column12 = column12;
    }

    public String getColumn13() {
        return column13;
    }

    public void setColumn13(String column13) {
        this.column13 = column13;
    }

    public String getColumn14() {
        return column14;
    }

    public void setColumn14(String column14) {
        this.column14 = column14;
    }

    public String getColumn15() {
        return column15;
    }

    public void setColumn15(String column15) {
        this.column15 = column15;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", column01='" + column01 + '\'' +
                ", column02='" + column02 + '\'' +
                ", column03='" + column03 + '\'' +
                ", column04='" + column04 + '\'' +
                ", column05='" + column05 + '\'' +
                ", timeStr=" + timeStr +
                ", column06='" + column06 + '\'' +
                ", column07='" + column07 + '\'' +
                ", column08='" + column08 + '\'' +
                ", column09='" + column09 + '\'' +
                ", column10='" + column10 + '\'' +
                ", column11='" + column11 + '\'' +
                ", column12='" + column12 + '\'' +
                ", column13='" + column13 + '\'' +
                ", column14='" + column14 + '\'' +
                ", column15='" + column15 + '\'' +
                '}';
    }
}
