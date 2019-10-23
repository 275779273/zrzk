package com.task.service.impl;

import com.task.dao.WaterMeterDao;
import com.task.pojo.WaterMeter;
import com.task.service.WaterMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WaterMeterServiceImpl implements WaterMeterService {

    private String[] genreNames = {"索引0占位符", "路面积水", "开盖报警器", "井下液位计", "小水表", "大水表", "压力变送器", "电磁流量计", "不锈钢用户水表", "主管道水表DN50", "阀控水表", "用户水表", "电磁流量计DN50"};

    @Autowired
    private WaterMeterDao waterMeterDao;

    @Override
    public Integer save() {
        Integer saveNum = 0;


        //i相当于genreId
        for (int i = 4; i < 13; i++) {
            if (i == 9 || i == 5) {
                continue;
            }
            Long count = waterMeterDao.getCount(i);
            List<String> codeArr;
            //10个设备的编号
            if (count == null || count == 0) {
                //数据库没有此类设备,就生成10个设备编号
                codeArr = getCodeArr(i);
            } else {
                //数据库有此类设备,就用已有的设备编号进行添加数据
                codeArr = waterMeterDao.getCode(i);
            }
            for (int j = 0; j < codeArr.size(); j++) {
                WaterMeter waterMeter = getObj(i, codeArr.get(j));
                saveNum = waterMeterDao.save(waterMeter);
            }
        }
        return saveNum;
    }

    /**
     * 根据genreId查询每个小水表,大水表的总和
     *
     * @param genreId
     * @return
     */
    @Override
    public Double findSum(Integer genreId) {
        return waterMeterDao.findSum(genreId);
    }

    /**
     * 根据genreId添加每个小水表,大水表的总和
     *
     * @param genreId
     * @param total
     * @return
     */
    @Override
    public Integer saveTotal(Integer genreId, Double total, Date insertTime) {
        return waterMeterDao.saveTotal(genreId, total, insertTime);
    }

    /**
     * 查询所有小水表的总和
     *
     * @param genreIdList
     * @return
     */
    @Override
    public Double findAllSum(Integer[] genreIdList) {
        return waterMeterDao.findAllSum(genreIdList[0], genreIdList[1], genreIdList[2]);
    }

    @Override
    public Integer saveBigTotal(Integer[] genreIdList2, Double sumTotal, Date date) {
        Integer integer1 = 0;
        for (Integer integer : genreIdList2) {
            integer1 = waterMeterDao.saveTotal(integer, sumTotal, new Date());
        }
        return integer1;
    }

    /**
     * 获取10个随机编号
     *
     * @return
     */
    public List<String> getCodeArr(Integer genreId) {
        List<String> codeArr = new ArrayList<>();
        while (codeArr.size() < 1) {
            //编号
            int code = (int) (Math.random() * 1000000);
            String codeStr = genreId + "" + code;
            if (!codeArr.contains(codeStr)) {
                codeArr.add(codeStr);
            }
        }
        return codeArr;
    }

    public WaterMeter getObj(Integer genreId, String code) {
        WaterMeter waterMeter = new WaterMeter();
        //水表类
        waterMeter.setGenreId(genreId);
        waterMeter.setGenreName(genreNames[genreId]);
        if (genreId == 4) {
            waterMeter.setNumber(Math.random() * 9);
            waterMeter.setUnitName("m³");
            waterMeter.setStatus(1);
            waterMeter.setTitle("水表");
            waterMeter.setImgUrl("imgs/waterMeter.png");
            waterMeter.setEquipmentCode(code);
            waterMeter.setInsertTime(new Date());
            //waterMeter.setStress(null);
            //waterMeter.setTemperature(null);
        }
        if (genreId == 5) {     //大水表
            waterMeter.setNumber(Math.random() * 9);
            waterMeter.setUnitName("m³");
            waterMeter.setStatus(1);
            waterMeter.setTitle("水表");
            waterMeter.setImgUrl("imgs/waterMeter.png");
            waterMeter.setEquipmentCode(code);
            waterMeter.setInsertTime(new Date());
            //waterMeter.setStress(null);
            //waterMeter.setTemperature(null);
        }
        if (genreId == 6) {
            waterMeter.setNumber(Math.random() * 3);
            waterMeter.setUnitName("MPa");
            waterMeter.setStatus(1);
            waterMeter.setTitle("水表");
            waterMeter.setImgUrl("imgs/waterMeter.png");
            waterMeter.setEquipmentCode(code);
            waterMeter.setInsertTime(new Date());
            waterMeter.setStress(0.0);
            waterMeter.setTemperature(waterMeter.getNumber() * 15);
        }
        if (genreId == 7) {
            waterMeter.setNumber(Math.random() * 9);
            waterMeter.setUnitName("m³");
            waterMeter.setStatus(1);
            waterMeter.setTitle("水表");
            waterMeter.setImgUrl("imgs/waterMeter.png");
            waterMeter.setEquipmentCode(code);
            waterMeter.setInsertTime(new Date());
            waterMeter.setStress(0.0);
            waterMeter.setTemperature(waterMeter.getNumber() * 15);
        }
        if (genreId == 8) {     //不锈钢用户水表(小水表)
            waterMeter.setNumber(getNumber(genreId));
            waterMeter.setUnitName("m³");
            waterMeter.setStatus(1);
            waterMeter.setTitle("水表");
            waterMeter.setImgUrl("imgs/waterMeter.png");
            waterMeter.setEquipmentCode(code);
            waterMeter.setInsertTime(new Date());
            //waterMeter.setStress(null);
            //waterMeter.setTemperature(null);
        }
        if (genreId == 9) {         //主管道水表(大水表)

            waterMeter.setNumber(getNumber(genreId));
            waterMeter.setUnitName("m³");
            waterMeter.setStatus(1);
            waterMeter.setTitle("水表");
            waterMeter.setImgUrl("imgs/waterMeter.png");
            waterMeter.setEquipmentCode(code);
            waterMeter.setInsertTime(new Date());
            //waterMeter.setStress(null);
            //waterMeter.setTemperature(null);
        }
        if (genreId == 10) {        //阀控水表(小水表)
            waterMeter.setNumber(getNumber(genreId));
            waterMeter.setUnitName("m³");
            waterMeter.setStatus(1);
            waterMeter.setTitle("水表");
            waterMeter.setImgUrl("imgs/waterMeter.png");
            waterMeter.setEquipmentCode(code);
            waterMeter.setInsertTime(new Date());
            //waterMeter.setStress(null);
            //waterMeter.setTemperature(null);
        }
        if (genreId == 11) {        //用户水表(小水表)
            waterMeter.setNumber(getNumber(genreId));
            waterMeter.setUnitName("m³");
            waterMeter.setStatus(1);
            waterMeter.setTitle("水表");
            waterMeter.setImgUrl("imgs/waterMeter.png");
            waterMeter.setEquipmentCode(code);
            waterMeter.setInsertTime(new Date());
            //waterMeter.setStress(null);
            //waterMeter.setTemperature(null);
        }
        if (genreId == 12) {        //电磁流量计DN50(和大水表数据一样)
            waterMeter.setNumber(getNumber(genreId));
            waterMeter.setUnitName("m³");
            waterMeter.setStatus(1);
            waterMeter.setTitle("水表");
            waterMeter.setImgUrl("imgs/waterMeter.png");
            waterMeter.setEquipmentCode(code);
            waterMeter.setInsertTime(new Date());
            waterMeter.setStress(0.0);
            waterMeter.setTemperature(waterMeter.getNumber() * 15);

            save9(waterMeter.getNumber());      //主管道水表DN50
            save5(waterMeter.getNumber());      //大水表
        }
        return waterMeter;
    }

    private Integer save9(Double number) {
        WaterMeter waterMeter = new WaterMeter();
        Long count = waterMeterDao.getCount(9);
        String codeStr = "0";
        //10个设备的编号
        if (count == null || count == 0) {
            //数据库没有此类设备,就生成10个设备编号
            int code = (int) (Math.random() * 1000000);
            codeStr = 9 + "" + code;
        } else {
            //数据库有此类设备,就用已有的设备编号进行添加数据
            List<String> codeList = waterMeterDao.getCode(9);
            codeStr = codeList.get(0);
        }

        waterMeter.setGenreId(9);
        waterMeter.setGenreName(genreNames[9]);
        waterMeter.setUnitName("m³");
        waterMeter.setStatus(1);
        waterMeter.setTitle("水表");
        waterMeter.setImgUrl("imgs/waterMeter.png");
        waterMeter.setEquipmentCode(codeStr);
        waterMeter.setInsertTime(new Date());
        waterMeter.setNumber(number);
        Integer num = waterMeterDao.save(waterMeter);
        return num;
    }

    private Integer save5(Double number) {
        WaterMeter waterMeter = new WaterMeter();
        Long count = waterMeterDao.getCount(5);
        String codeStr = "0";
        //10个设备的编号
        if (count == null || count == 0) {
            //数据库没有此类设备,就生成10个设备编号
            int code = (int) (Math.random() * 1000000);
            codeStr = 5 + "" + code;
        } else {
            //数据库有此类设备,就用已有的设备编号进行添加数据
            List<String> codeList = waterMeterDao.getCode(5);
            codeStr = codeList.get(0);
        }

        waterMeter.setGenreId(5);
        waterMeter.setGenreName(genreNames[5]);
        waterMeter.setUnitName("m³");
        waterMeter.setStatus(1);
        waterMeter.setTitle("水表");
        waterMeter.setImgUrl("imgs/waterMeter.png");
        waterMeter.setEquipmentCode(codeStr);
        waterMeter.setInsertTime(new Date());
        waterMeter.setNumber(number);
        Integer num = waterMeterDao.save(waterMeter);
        return num;
    }

    public Double getNumber(Integer genreId) {
        double[] genreId8 = {0, 0, 0, 0, 0.01, 0.02, 0.01, 0.09, 0.03, 0.05, 0.02, 0.12, 0.15, 0.17, 0.02, 0.03, 0.06, 0.1, 0.07, 0.05, 0.07, 0.02, 0, 0, 0};
        double[] genreId10 = {0, 0, 0, 0, 0, 0.02, 0.03, 0.07, 0.1, 0.04, 0.06, 0.03, 0.13, 0.16, 0.18, 0.03, 0.04, 0.07, 0.11, 0.08, 0.06, 0.01, 0, 0, 0};
        double[] genreId11 = {0, 0, 0, 0, 0, 0, 0.04, 0.04, 0.08, 0.11, 0.05, 0.07, 0.04, 0.14, 0.17, 0.16, 0.04, 0.05, 0.07, 0.12, 0.07, 0.04, 0.06, 0.01, 0};
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String time = sdf.format(date);
        //当前时间(小时)
        int timeNum = Integer.parseInt(time);
        if (genreId == 8) { //不锈钢用户水表

            return genreId8[timeNum];
        }
        if (genreId == 10) {    //阀控水表
            return genreId10[timeNum];
        }
        if (genreId == 11) {    //用户水表
            return genreId11[timeNum];
        }
        if (genreId == 12) {
            Double sum = waterMeterDao.findNumberByGenreId(8, 10, 11);
            //System.out.println("genreId = " + genreId);
            System.out.println("sum = " + sum);
            if (sum == null) {
                return 0.0;
            } else {
                return sum;
            }
        }
        return 0.0;
    }
}
