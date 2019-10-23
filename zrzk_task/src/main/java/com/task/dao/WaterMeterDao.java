package com.task.dao;

import com.task.pojo.WaterMeter;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface WaterMeterDao {
    /**
     * 查询所有
     *
     * @return
     */
    @Select(value = "SELECT id,genreId,genreName,number,unitName,status,title,imgUrl,equipmentCode,insertTime,stress,temperature FROM zrzk_water ")
    List<WaterMeter> findAll();

    /**
     * 添加
     *
     * @param waterMeter
     */
    @Insert(value = "INSERT INTO zrzk_water (genreId , genreName , number , unitName , STATUS , title , imgUrl , equipmentCode , insertTime,stress,temperature) " +
            "VALUES(#{genreId},#{genreName},#{number},#{unitName},#{status},#{title},#{imgUrl},#{equipmentCode},#{insertTime},#{stress},#{temperature}) ")
    Integer save(WaterMeter waterMeter);

    @Select(value = "select count(1) from zrzk_water where genreId=#{genreId} ")
    Long getCount(Integer genreId);

    @Select(value = "select equipmentCode from zrzk_water where genreId=#{genreId} GROUP BY equipmentCode ")
    List<String> getCode(Integer genreId);

    /**
     * 查询number总和
     * @param id1
     * @param id2
     * @param id3
     * @return
     */
    //@Select(value = "SELECT SUM(number) FROM zrzk_water WHERE TO_DAYS(insertTime) = TO_DAYS(NOW()) AND genreId IN(#{id1},#{id2},#{id3}) ")
    @Select(value = "SELECT SUM(number) FROM zrzk_water WHERE genreId IN(#{id1},#{id2},#{id3}) ")
    Double findNumberByGenreId(Integer id1,Integer id2,Integer id3);

    /**
     * 根据genreId查询每个小水表,大水表的总和
     * @param genreId
     * @return
     */
    @Select(value = "SELECT SUM(number) FROM zrzk_water WHERE genreId=#{genreId}")
    Double findSum(Integer genreId);

    /**
     * 根据genreId添加每个小水表,大水表的总和
     * @param genreId
     * @param total
     * @return
     */
    @Insert(value = "INSERT INTO zrzk_total (genreId,total,insertTime)VALUES(#{genreId},#{total},#{insertTime}) ")
    Integer saveTotal(@Param("genreId")Integer genreId, @Param("total")Double total, @Param("insertTime")Date insertTime);

    /**
     * 查询所有小水表的总和
     * @param id1
     * @param id2
     * @param id3
     * @return
     */
    @Select(value = "SELECT SUM(total) FROM (SELECT total FROM (SELECT genreId,total,insertTime  FROM zrzk_total WHERE genreId IN (#{id1},#{id2},#{id3}) ORDER BY insertTime DESC  ) a GROUP BY genreId) b ")
    Double findAllSum(@Param("id1") Integer id1, @Param("id2") Integer id2, @Param("id3") Integer id3);
}
