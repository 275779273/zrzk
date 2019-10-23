/*
package com.zrzk.baidumap.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;

import sun.misc.BASE64Encoder;


public class MapToXml {
    */
/**
     * 将map依照层次转化
     * @param map
     * @param rootElement
     *//*

    public static void loopMap(Map map,Element rootElement){
        Iterator<String> it = map.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            String typeName =map.get(key).getClass().getName();
            System.out.println(key);
            System.out.println(getType(typeName));
            if(!getType(typeName).equals("LinkedHashMap")){
                //if(!getType(typeName).equals("JSONObject")){
                if(key.startsWith("Receivers")){        //特殊处理，传输xml多个接收者的情况
                    Element keyElement = rootElement.addElement("Receiver");
                    keyElement.setText( (String) map.get(key));
                }
                else{
                    Element keyElement = rootElement.addElement(key);
                    keyElement.setText((String) map.get(key));
                }
            }
            else if(getType(typeName).equals("LinkedHashMap")){
                //else if(getType(typeName).equals("JSONObject")){
                if(key.equals("ApplyDetail")){						//特殊处理
                    Element keyElement0 = rootElement.addElement(key);
                    Element keyElement = keyElement0.addElement(key);
                    Map map2 = (Map) map.get(key);
                    loopMap(map2,keyElement);
                }
                else{
                    Element keyElement = rootElement.addElement(key);
                    Map map2 = (Map) map.get(key);
                    loopMap(map2,keyElement);
                }
            }
        }
    }
    */
/**
     * 获取数据类型，用于判断是否添加子元素
     * @param typeName
     * @return
     *//*

    public static String getType(String typeName){
        int length= typeName.lastIndexOf(".");
        String type =typeName.substring(length+1);
        return typeName.substring(length+1);
    }
    */
/**
     * 解决默认第二行为空的情况
     * @return
     *//*

    public static OutputFormat getForm(){
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setIndentSize(4);//改为4
        format.setNewLineAfterDeclaration(false);
        return format;
    }
————————————————
    版权声明：本文为CSDN博主「FD-Do」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/kunfd/article/details/81369763
}
*/
