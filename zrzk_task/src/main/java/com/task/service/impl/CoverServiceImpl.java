package com.task.service.impl;

import com.task.dao.CoverDao;
import com.task.dao.RegionDao;
import com.task.pojo.Cover;
import com.task.pojo.Region;
import com.task.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoverServiceImpl implements CoverService {

    private String[] genreNames = {"索引0占位符", "路面积水", "开盖报警器", "井下液位计", "小水表", "大水表", "压力变送器", "电磁流量计", "不锈钢用户水表", "主管道水表DN50", "阀控水表", "用户水表", "电磁流量计DN50"};

    @Autowired
    private CoverDao coverDao;
    @Autowired
    private RegionDao regionDao;

    public List findAll() {
        return coverDao.findAll();
    }

    @Override
    public Integer save() {
        Integer saveNum = 0;
        //i相当于genreId
        for (int i = 1; i < 4; i++) {
            Long count = coverDao.getCount(i);

            List<String> codeArr;
            //10个设备的编号
            if (count == null || count == 0) {
                //数据库没有此类设备,就生成10个设备编号
                codeArr = getCodeArr(i);
            } else {
                //数据库有此类设备,就用已有的设备编号进行添加数据
                codeArr = coverDao.getCode(i);
            }
            //
            for (int j = 0; j < codeArr.size(); j++) {
                Cover cover = getObj(i, codeArr.get(j));
                saveNum = coverDao.save(cover);
            }
        }
        return saveNum;
    }

    /**
     * 获取自定义个数随机编号
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

    public Cover getObj(Integer genreId, String code) {
        Cover cover = new Cover();
        //井盖类
        cover.setGenreId(genreId);
        cover.setGenreName(genreNames[genreId]);
        if (genreId == 1) {     //路面积水
            cover.setNumber(Math.random() * 0.13);
            cover.setUnitName("M");
            cover.setStatus(getStatus(genreId,cover.getNumber()));
            cover.setTitle("井盖");
            cover.setImgUrl("imgs/cover.png");
            cover.setEquipmentCode(code);
            cover.setInsertTime(new Date());
            cover.setTemperature(21.2);
            //cover.setMeasuredNumber(null);
        }
        if (genreId == 2) {     //开盖报警器
            cover.setNumber(Math.random() * 15);
            cover.setUnitName("°");
            cover.setStatus(getStatus(genreId,cover.getNumber()));
            cover.setTitle("井盖");
            cover.setImgUrl("imgs/cover.png");
            cover.setEquipmentCode(code);
            cover.setInsertTime(new Date());
            //cover.setTemperature(null);
            //cover.setMeasuredNumber(null);
        }
        if (genreId == 3) {     //井下液位计
            cover.setNumber(Math.random() * 0.7);
            cover.setUnitName("M");
            cover.setStatus(getStatus(genreId,cover.getNumber()));
            cover.setTitle("井盖");
            cover.setImgUrl("imgs/cover.png");
            cover.setEquipmentCode(code);
            cover.setInsertTime(new Date());
            cover.setTemperature(25.6);
            cover.setMeasuredNumber(cover.getNumber());
        }
        return cover;
    }

    public int getStatus(Integer genreId,Double number){
        Region region = regionDao.findByGenreId(genreId);
        if(region.getSmall()<=number&&number<=region.getBig()){
            return 1;
        }else {
            return 0;
        }
    }
}
