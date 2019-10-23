package com.task.service;


import java.util.Date;

public interface WaterMeterService {


    /**
     * 添加数据
     * @param
     */
    Integer save();

    /**
     * 根据genreId查询每个小水表,大水表的总和
     * @param genreId
     * @return
     */
    Double findSum(Integer genreId);

    Integer saveTotal(Integer genreId, Double total, Date insertTime);

    /**
     * 查询所有小水表的总和
     * @param genreIdList
     * @return
     */
    Double findAllSum(Integer[] genreIdList);

    /**
     * 存入大水表的数据
     * @param genreIdList2
     * @param sumTotal
     * @param date
     * @return
     */
    Integer saveBigTotal(Integer[] genreIdList2, Double sumTotal, Date date);
}
