package com.task.savetask;

import com.task.dao.WaterMeterDao;
import com.task.service.CoverService;
import com.task.service.WaterMeterService;
import com.task.service.impl.CoverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
//开启定时任务
@EnableScheduling
@EnableAsync
public class SaveTask {

    private int num1 = 0;
    private int num2 = 0;

    @Autowired
    private CoverService coverService;
    @Autowired
    private WaterMeterService waterMeterService;

    @Autowired
    private WaterMeterDao waterMeterDao;


    //每秒钟执行一次
    //@Scheduled(cron = "0/5 * * * * ?")
    //每个整点执行一次
    //每个小时的30分钟执行一次
    @Scheduled(cron = "0 30 0/1 * * ?")
    @Async
    public void saveCover() {
        Integer integer = 0;
        Integer[] genreIdList = {8, 10, 11};
        Integer[] genreIdList2 = {5,9,12};
        //Integer save1 = coverService.save();

        //Integer save2 = waterMeterService.save();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        System.out.println("=================================");
        //if (save1 > 0) {
        //    num1 = ++num1;
        //    System.out.println("井盖添加成功" + num1 + "添加时间" + time);
        //}
//        if (save2 > 0) {
//            num2 = ++num2;
//            System.out.println("水表添加成功" + num2 + "添加时间" + time);
//            for (int i = 0; i < genreIdList.length; i++) {
//                //添加总和表的数据
//                Double sum = waterMeterService.findSum(genreIdList[i]);
//                integer = waterMeterService.saveTotal(genreIdList[i], sum, new Date());
//            }
//            if (integer > 0) {
//                //查询所有小水表的累计流量 zrzk_total表
//                Double sumTotal = waterMeterService.findAllSum(genreIdList);
//                //存入大水表的数据
//                Integer integer1 = waterMeterService.saveBigTotal(genreIdList2,sumTotal,new Date());
//            }
//
//        }

    }


}
