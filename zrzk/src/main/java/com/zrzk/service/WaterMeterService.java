package com.zrzk.service;


import com.zrzk.pojo.WaterMeter;

import java.util.Date;
import java.util.List;

public interface WaterMeterService {
    /**
     * 查询所有水表信息
     *
     * @return
     */
    List findAll();

    /**
     * 添加数据
     *
     * @param waterMeter
     */
    Integer save(WaterMeter waterMeter);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    List findById(Integer id);

    Integer deleteAll();

    Integer deleteById(Integer id);

    List<WaterMeter> findByGenreIdAndCode(Integer genreId, String code);

    List findByGenreId(Integer genreId);

    List findByCode(String code);

    List<WaterMeter> findByGIdToday(Integer genreId);

    /**
     * 根据genreId添加每个小水表,大水表的总和(手动添加数据用)
     * @param genreList
     * @param totalList
     * @return
     */
    Integer saveTotal(List<Integer> genreList, List<Double> totalList);

    Double findAllSum(Integer id1,Integer id2,Integer id3);
}
