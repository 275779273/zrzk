package com.zrzk.baidumap.service;


import com.zrzk.baidumap.utils.HttpClient;

import javax.xml.bind.Element;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class BaiDuMapService {
    public static void main(String[] args) throws IOException, ParseException {
        HttpClient httpClient = new HttpClient("http://api.map.baidu.com/place/v2/search?query=%E5%A4%A9%E5%AE%89%E9%97%A8&region=%E5%8C%97%E4%BA%AC&output=json&ak=rZvIGI1RUk4d2DjflRtRFuiLTRGkKgvz");     //?query=%E5%A4%A9%E5%AE%89%E9%97%A8&region=%E5%8C%97%E4%BA%AC&output=json&ak=rZvIGI1RUk4d2DjflRtRFuiLTRGkKgvz
        //HttpClient httpClient = new HttpClient("http://api.map.baidu.com/place/v2/search?query=天安门&region=北京&output=json&ak=rZvIGI1RUk4d2DjflRtRFuiLTRGkKgvz");     //?query=%E5%A4%A9%E5%AE%89%E9%97%A8&region=%E5%8C%97%E4%BA%AC&output=json&ak=rZvIGI1RUk4d2DjflRtRFuiLTRGkKgvz
        //HttpClient httpClient = new HttpClient("http://api.map.baidu.com/place/v2/search");     //?query=%E5%A4%A9%E5%AE%89%E9%97%A8&region=%E5%8C%97%E4%BA%AC&output=json&ak=rZvIGI1RUk4d2DjflRtRFuiLTRGkKgvz

      // HashMap<String,String> map = new HashMap<>();
      //  map.put("query","天安门");
      //  map.put("region","北京");
      //  map.put("output","json");
      //  map.put("ak","rZvIGI1RUk4d2DjflRtRFuiLTRGkKgvz");
        /*httpClient.setParameter(map);*/
        //httpClient.setXmlParam();
        httpClient.setHttps(true);
        httpClient.post();
        //Element
        String content = httpClient.getContent();
        System.out.println("content = " + content);
    }
}
