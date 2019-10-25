package com.zrzk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zrzk.pojo.Result;
import com.zrzk.pojo.WaterMeter;
import com.zrzk.service.impl.WaterMeterServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

//水表
@RestController
@RequestMapping("/waterMeter")
public class WaterMeterController {
    @Autowired
    private WaterMeterServiceImpl waterMeterService;

    /**
     * 查询所有设备最新的数据
     *
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAllCover() {
        List coverList = waterMeterService.findAll();
        if (coverList != null && coverList.size() > 0) {
            return new Result(true, "查询到数据", coverList);
        } else {
            return new Result(false, "未查到数据");
        }
    }

    /**
     * 添加
     *
     * @param waterMeter
     * @return
     */
    @RequestMapping("/save")
    public Result saveWaterMeter(WaterMeter waterMeter) {
        Integer integer = waterMeterService.save(waterMeter);
        if (integer > 0) {
            return new Result(true, "添加数据成功");
        } else {
            return new Result(false, "添加数据失败");
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById/{id}")
    public Result findWaterMeterById(@PathVariable Integer id) {
        List waterMeterList = waterMeterService.findById(id);
        if (waterMeterList != null && waterMeterList.size() > 0) {
            return new Result(true, "查询到数据", waterMeterList);
        } else {
            return new Result(false, "未查询到数据");
        }
    }

    /**
     * 删除所有数据
     *
     * @return
     */
    @RequestMapping("/deleteAll")
    public Result deleteAll() {
        Integer integer = waterMeterService.deleteAll();
        if (integer > 0) {
            return new Result(true, "删除数据成功");
        } else {
            return new Result(false, "删除数据失败");
        }
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id) {
        Integer integer = waterMeterService.deleteById(id);
        if (integer > 0) {
            return new Result(true, "删除数据成功");
        } else {
            return new Result(false, "删除数据失败");
        }
    }

    /**
     * 根据类型id查询每台设备数据的总和
     *
     * @return
     */
    @RequestMapping("/findByGenreId/{genreId}")
    public Result findByGenreId(@PathVariable Integer genreId) {
        //List waterMeterList = waterMeterService.findByGenreId(genreId);
        List waterMeterList = waterMeterService.findByGenreId(genreId);
        if (waterMeterList != null && waterMeterList.size() > 0) {
            return new Result(true, "查询到数据", waterMeterList);
        } else {
            return new Result(false, "未查询到数据");
        }
    }

    /**
     * 根据类型id和水表编号查询
     *
     * @param genreId
     * @param code
     * @return
     */
    @RequestMapping("/findByGenreIdAndCode/{genreId}/{code}")
    public Result findByGenreIdAndCode(@PathVariable Integer genreId, @PathVariable String code) {
        List<WaterMeter> waterMeterList = waterMeterService.findByGenreIdAndCode(genreId, code);
        if (waterMeterList != null && waterMeterList.size() > 0) {
            return new Result(true, "查询到数据", waterMeterList);
        } else {
            return new Result(false, "未查询到数据");
        }
    }

    /**
     * 根据水表编号查询
     *
     * @param code
     * @return
     */
    @RequestMapping("/findByCode/{code}")
    public Result findByCode(@PathVariable String code) {
        List waterMeterList = waterMeterService.findByCode(code);
        if (waterMeterList != null && waterMeterList.size() > 0) {
            return new Result(true, "查询到数据", waterMeterList);
        } else {
            return new Result(false, "未查询到数据");
        }
    }

    /**
     * 根据类型id查询设备当天的所有数据
     *
     * @param genreId
     * @return
     */
    @RequestMapping("/findByGIdToday/{genreId}")
    public Result findByGIdToday(@PathVariable Integer genreId) {
        List<WaterMeter> waterMeterList = waterMeterService.findByGIdToday(genreId);
        if (waterMeterList != null && waterMeterList.size() > 0) {
            return new Result(true, "查询到数据", waterMeterList);
        } else {
            return new Result(false, "未查询到数据");
        }
    }

    /**
     * 根据genreId添加每个小水表,大水表的总和(手动添加数据用)
     *
     * @param map
     * @return
     */
    @RequestMapping("/saveTotal")
    public synchronized Result saveTotal(@RequestParam Map map, HttpServletResponse response) {
        //解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

        List<Integer> genreIdList = new ArrayList();
        List<Double> totalList = new ArrayList();
        Set set = map.keySet();
        for (Object obj : set) {
            Object value = map.get(obj);
            int genreId = Integer.parseInt((String) obj);
            System.out.println("value = " + value);
            double total = Double.parseDouble((String) value);

            genreIdList.add(genreId);
            totalList.add(total);
        }
        Integer integer = waterMeterService.saveTotal(genreIdList, totalList);

        //if (integer > 0) {   //添加成功
        //    //添加大水表总数
        //    Double sum = waterMeterService.findAllSum(8, 10, 11);
        //    Integer integer1 = waterMeterDao.saveTotal(5, sum, new Date());
        //    Integer integer2 = waterMeterDao.saveTotal(9, sum, new Date());
        //    Integer integer3 = waterMeterDao.saveTotal(12, sum, new Date());
        //    if (integer1 > 0 && integer2 > 0 && integer3 > 0) {
        //        return new Result(true, "添加数据成功");
        //    }
        //}
        if (integer > 0) {   //添加成功
            return new Result(true, "添加数据成功");
        }else {
            return new Result(false, "添加数据失败");

        }
    }
}
