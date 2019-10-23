package com.zrzk.baidumap.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String dateToString(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    public static Date stringToDate(String dateStr,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse=null;
        try {
            parse = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
