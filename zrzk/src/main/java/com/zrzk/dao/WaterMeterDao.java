package com.zrzk.dao;


import org.apache.ibatis.annotations.*;
import com.zrzk.pojo.WaterMeter;

import java.util.List;

@Mapper
public interface WaterMeterDao {
    /**
     * 查询所有水表最新的数据
     *
     * @return
     */
    @Select(value = "SELECT id,genreId,genreName,number,STATUS,title,imgUrl,equipmentCode,insertTime,stressNum,temperatureNum FROM (SELECT id,genreId,genreName,number,STATUS,title,imgUrl,equipmentCode,insertTime,stressNum,temperatureNum FROM zrzk_total ORDER BY insertTime DESC) a GROUP BY genreId ")
    List<WaterMeter> findAll();

    /**
     * 添加
     *
     * @param waterMeter
     */
    @Insert(value = "INSERT INTO zrzk_water (genreId , genreName , number , unitName , STATUS , title , imgUrl , equipmentCode , insertTime,stressNum,temperatureNum) " +
            "VALUES(#{genreId},#{genreName},#{number},#{unitName},#{status},#{title},#{imgUrl},#{equipmentCode},#{insertTime},#{stressNum},#{temperatureNum}) ")
    Integer save(WaterMeter waterMeter);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Select(value = "SELECT id,genreId,genreName,number,unitName,status,title,imgUrl,equipmentCode,insertTime,stressNum,temperatureNum FROM zrzk_water WHERE id=#{id} ")
    List<WaterMeter> findById(@Param("id") Integer id);

    /**
     * 删除所有数据
     * @return
     */
    @Delete(value = "DELETE FROM zrzk_water ")
    Integer deleteAll();

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Delete(value = "DELETE FROM zrzk_water WHERE id=#{id} ")
    Integer deleteById(Integer id);

    @Select(value = "select count(1) from zrzk_water")
    Long getCount();

    /**
     * 根据类型id查询每个设备最新的一条数据
     * @param genreId
     * @return
     */
    //总和
    @Select(value = "SELECT id,genreId,genreName,number,unitName,STATUS,title,imgUrl,equipmentCode,insertTime,stressNum,temperatureNum FROM (SELECT id,genreId,genreName,number,unitName,STATUS,title,imgUrl,equipmentCode,insertTime,stressNum,temperatureNum FROM zrzk_water ORDER BY insertTime DESC) a WHERE genreId=#{genreId} GROUP BY equipmentCode ")
    List<WaterMeter> findByGenreId(Integer genreId);

    /**
     * 根据类型id和井盖编号查询
     * @param genreId
     * @param code
     * @return
     */
    @Select(value = "SELECT id,genreId,genreName,number,unitName,status,title,imgUrl,equipmentCode,insertTime,stressNum,temperatureNum FROM zrzk_water WHERE genreId=#{genreId} and equipmentCode=#{code}")
    List<WaterMeter> findByGenreIdAndCode(@Param("genreId") Integer genreId,@Param("code")String code);

    /**
     * 根据水表编号查询
     * @param code
     * @return
     */
    @Select(value = "SELECT id,genreId,genreName,number,unitName,status,title,imgUrl,equipmentCode,insertTime,stressNum,temperatureNum FROM zrzk_water WHERE equipmentCode=#{code}")
    List<WaterMeter> findByCode(String code);

    /**
     * 根据类型id查询每个设备当天的所有数据
     * @param genreId
     * @return
     */
    @Select(value = "SELECT id,genreId,genreName,number,unitName,status,title,imgUrl,equipmentCode,insertTime,stressNum,temperatureNum FROM zrzk_water WHERE TO_DAYS(insertTime) = TO_DAYS(NOW()) AND genreId=#{genreId} ORDER BY insertTime ASC")
    List<WaterMeter> findByGIdToday(Integer genreId);

    /**
     * 根据genreId查询总和表
     * @param genreId
     * @return
     */
    @Select(value = "SELECT total FROM (SELECT genreId,total,insertTime  FROM zrzk_total ORDER BY insertTime DESC) a WHERE genreId=#{genreId} GROUP BY genreId ")
    Double findSum(Integer genreId);

    /**
     * 查询所有小水表的总和
     * @return
     */
    //@Select(value = "SELECT SUM(total) FROM (SELECT genreId,total,insertTime  FROM zrzk_total ORDER BY insertTime DESC) a WHERE genreId IN(#{id1},#{id2},#{id3})")
    @Select(value = "SELECT SUM(total) FROM (SELECT total FROM (SELECT genreId,total,insertTime  FROM zrzk_total WHERE genreId IN (#{id1},#{id2},#{id3}) ORDER BY insertTime DESC  ) a GROUP BY genreId) b ")
    Double findAllSum(@Param("id1")Integer id1,@Param("id2")Integer id2,@Param("id3")Integer id3);

    /**
     * 根据genreId添加每个小水表,大水表的总和(手动添加数据用)
     * @param waterMeter
     * @return
     */
    @Insert(value = "INSERT INTO zrzk_total (genreId , genreName , number , unitName , STATUS , title , imgUrl , equipmentCode , insertTime,stressNum,temperatureNum) " +
            "VALUES(#{genreId},#{genreName},#{number},#{unitName},#{status},#{title},#{imgUrl},#{equipmentCode},#{insertTime},#{stressNum},#{temperatureNum}) ")
    Integer saveTotal(WaterMeter waterMeter);

    /**
     * 查询total所有设备最新的数据
     * @param genreId
     * @return
     */
    @Select(value = "SELECT id,genreId,genreName,number,unitName,STATUS,title,imgUrl,equipmentCode,insertTime,stressNum,temperatureNum FROM (SELECT id,genreId,genreName,number,unitName,STATUS,title,imgUrl,equipmentCode,insertTime,stressNum,temperatureNum FROM zrzk_total WHERE genreId=#{genreId} ORDER BY insertTime DESC ) a GROUP BY a.genreId ")
    List<WaterMeter> findNewTotal(Integer genreId);
}
