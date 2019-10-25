package com.zrzk.service.impl;

import com.zrzk.dao.EquipmentDao;
import com.zrzk.pojo.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zrzk.dao.WaterMeterDao;
import com.zrzk.pojo.WaterMeter;
import com.zrzk.service.WaterMeterService;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WaterMeterServiceImpl implements WaterMeterService {
    //8不锈钢用户水表,10阀控水表,11用户水表
    private String[] genreNames = {"索引0占位符", "路面积水", "开盖报警器", "井下液位计", "小水表", "大水表", "压力变送器", "电磁流量计", "DN15小水表(市政)", "主管道水表DN50", "DN15小水表(社区)", "DN15小水表(工厂)", "电磁流量计DN50"};

    private String[] codes = {"", "1631456", "2463218", "3654923", "4354924", "5648324", "6513464", "7653497", "8695101", "9123942", "1068277", "1157513", "1219628"};

    @Autowired
    private WaterMeterDao waterMeterDao;

    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    public List findAll() {

        List<WaterMeter> waterMeterList = waterMeterDao.findAll();
        for (WaterMeter waterMeter : waterMeterList) {
            waterMeter.setLat("26.08375");
            waterMeter.setLng("119.32006");
        }
        return waterMeterList;
    }

    @Override
    public Integer save(WaterMeter waterMeter) {
        waterMeter.setInsertTime(new Date());
        return waterMeterDao.save(waterMeter);
    }

    @Override
    public List findById(Integer id) {
        return waterMeterDao.findById(id);
    }

    @Override
    public Integer deleteAll() {
        return waterMeterDao.deleteAll();
    }

    @Override
    public Integer deleteById(Integer id) {
        return waterMeterDao.deleteById(id);
    }

    @Override
    public List<WaterMeter> findByGenreIdAndCode(Integer genreId, String code) {
        return waterMeterDao.findByGenreIdAndCode(genreId, code);
    }

    @Override
    public List findByGenreId(Integer genreId) {
        List<WaterMeter> waterMeterList ;


        if (genreId==6 || genreId == 8 || genreId == 9 || genreId == 10 || genreId == 11 || genreId == 12) {    //大水表,小水表
            //根据类型id查询每台设备数据的总和
//            Double sum = waterMeterDao.findSum(genreId);
//            for (WaterMeter waterMeter : waterMeterList) {
//                waterMeter.setNumber(sum);
//            }
            //查询total所有设备最新的数据
            waterMeterList = waterMeterDao.findNewTotal(genreId);
            for (WaterMeter waterMeter : waterMeterList) {
                waterMeter.setInsertTime(getTime());
                waterMeter.setStressNum(null);      //设置压力
                if(genreId==6){
                    waterMeter.setInsertTime(null);         //设置时间
                    waterMeter.setTemperatureNum(null);     //设置温度
                    waterMeter.setStressNum(null);          //设置压力
                }
            }
        }else {
            waterMeterList = waterMeterDao.findByGenreId(genreId);
            for (WaterMeter waterMeter : waterMeterList) {
                    waterMeter.setInsertTime(getTime());


            }
        }
        return waterMeterList;
    }

    @Override
    public List findByCode(String code) {
        return waterMeterDao.findByCode(code);
    }

    @Override
    public List<WaterMeter> findByGIdToday(Integer genreId) {
        return waterMeterDao.findByGIdToday(genreId);
    }

    /**
     * 根据genreId添加每个小水表,大水表的总和(手动添加数据用)
     *
     * @param genreList 设备类型ID集合
     * @param totalList 设备数据集合
     * @return  int
     */
    @Override
    @Transactional
    public Integer saveTotal(List<Integer> genreList, List<Double> totalList) {
        WaterMeter waterMeter = new WaterMeter();
        waterMeter.setStatus(1);
        waterMeter.setTitle("水表");
        waterMeter.setImgUrl("imgs/waterMeter.png");
        waterMeter.setInsertTime(new Date());
        waterMeter.setStressNum(0.0);
        waterMeter.setTemperatureNum(23.0);
        Integer integer = 0;
        for (int i = 0; i < genreList.size(); i++) {
            waterMeter.setGenreId(genreList.get(i));
            waterMeter.setNumber(totalList.get(i));
            waterMeter.setGenreName(genreNames[genreList.get(i)]);
            waterMeter.setEquipmentCode(codes[genreList.get(i)]);
            integer = waterMeterDao.saveTotal(waterMeter);
            //
            Equipment equipment = new Equipment();
            //所在机构
            equipment.setEmployer("0109中润智控");
            //设备类别
            equipment.setEquipmentType("智能远传水表");
            //设备编号
            equipment.setEquipmentCode(codes[genreList.get(i)]);
            //设备名称
            equipment.setEquipmentName(genreNames[genreList.get(i)]);
            //imei
            equipment.setImei("152645565215896");
            //最后上报时间
            equipment.setReportTime(new Date());
            //报警
            equipment.setAlert("无");
            //状态
            equipment.setStatus(0);
            //上游平台
            equipment.setPlatform("电信");
            //DeviceID
            equipment.setDeviceId(codes[genreList.get(i)]);
            //经度
            equipment.setLongitude("119.32006");
            //纬度
            equipment.setLatitude("26.08375");

            int insert = equipmentDao.insert(equipment);
        }


        return integer;
    }

    //获取当天0点的时间
    private Date getTime() {
        try {
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

        //Date date = sdf.parse(startTime);
        return sdf.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Double findAllSum(Integer id1, Integer id2, Integer id3) {
        return waterMeterDao.findAllSum(id1, id2, id3);
    }
}
