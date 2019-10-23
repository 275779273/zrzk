package com.zrzk.baidumap.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDate {
    public static void main(String[] args) throws ParseException {
        //得到一个Calendar实例
        Calendar calendar = Calendar.getInstance();
        //calendar的日期设为今天
        calendar.setTime(new Date());
        //设置calendar为昨天
        calendar.add(Calendar.DATE, 0);
        //calendar.add(Calendar.YEAR, -1);//前一年
        //calendar.add(Calendar.MONTH, -1);//前一月

        Date yesterday = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //前一天的字符串
        String pre = sdf.format(yesterday);
        //前一天凌晨0点的字符串
        String startTime = pre.substring(0, 10) + " 00:00:00";
        //前一天午夜24点的字符串
        String endTime = pre.substring(0, 10) + " 24:00:00";

        Date date = sdf.parse(startTime);

        System.out.println("date = " + date);
        System.out.println("startTime = " + startTime);
    }
}
