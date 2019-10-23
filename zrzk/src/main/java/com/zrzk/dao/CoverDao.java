package com.zrzk.dao;


import org.apache.ibatis.annotations.*;
import com.zrzk.pojo.Cover;

import java.util.List;

@Mapper
public interface CoverDao {
    /**
     * 查询所有
     *
     * @return
     */
    @Select(value = "SELECT id,genreId,genreName,number,unitName,status,title,imgUrl,equipmentCode,insertTime,temperature,measuredNumber FROM zrzk_cover ")
    List<Cover> findAll();

    /**
     * 查询总条数
     * @return
     */
    @Select(value = "SELECT COUNT(1) FROM cover ")
    Long getCount();

    /**
     * 添加
     *
     * @param cover
     */
    @Insert(value = " INSERT INTO zrzk_cover (genreId , genreName , number , unitName , STATUS , title , imgUrl , equipmentCode , insertTime,temperature,measuredNumber) " +
            "VALUES(#{genreId},#{genreName},#{number},#{unitName},#{status},#{title},#{imgUrl},#{equipmentCode},#{insertTime},#{temperature},#{measuredNumber}) ")
    Integer save(Cover cover);

    /**
     * 根据id查询
     *
     * @param genreId
     * @return
     */
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "genreId",column = "column_01"),     //设备类型
            @Result(property = "number",column = "column_13"),      //数值
            @Result(property = "status",column = "column_10"),
            @Result(property = "equipmentCode",column = "column_02"),
            @Result(property = "insertTime",column = "column_05")
    })
    @Select(value = "SELECT id,column_01,column_02,column_03,column_04,column_05,column_06,column_07,column_08,column_09,column_10,column_11,column_12,column_13,column_14,column_15 FROM (SELECT id,column_01,column_02,column_03,column_04,column_05,column_06,column_07,column_08,column_09,column_10,column_11,column_12,column_13,column_14,column_15 FROM device_log WHERE column_01=#{genreId} AND column_02=#{code} ORDER BY column_05 DESC) a GROUP BY column_02")
    List<Cover> findByGenreId(@Param("genreId") Integer genreId ,@Param("code")String code );

    /**
     * 根据类型id查询每个设备最新的一条数据
     *
     * @param genreId
     * @return
     */
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "genreId",column = "column_01"),     //设备类型
            @Result(property = "number",column = "column_07"),      //数值
            @Result(property = "status",column = "column_03"),
            @Result(property = "equipmentCode",column = "column_02"),
            @Result(property = "insertTime",column = "column_05"),
            @Result(property = "temperature",column = "column_08"),
    })
    @Select(value = "SELECT id,column_01,column_02,column_03,column_04,column_05,column_06,column_07,column_08,column_09,column_10,column_11 FROM (SELECT id,column_01,column_02,column_03,column_04,column_05,column_06,column_07,column_08,column_09,column_10,column_11 FROM fluviograph_log WHERE column_01=#{genreId} AND column_02=#{code} ORDER BY column_05 DESC) a GROUP BY column_02")
    List<Cover> findByGenreId1(@Param("genreId") Integer genreId ,@Param("code")String code );

    /**
     * 删除所有井盖数据
     * @return
     */
    @Delete(value = "DELETE FROM zrzk_cover ")
    Integer deleteAll();

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Delete(value = "DELETE FROM zrzk_cover WHERE id=#{id} ")
    Integer deleteById(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select(value = "SELECT id,genreId,genreName,number,unitName,status,title,imgUrl,equipmentCode,insertTime,temperature,measuredNumber FROM zrzk_cover WHERE id=#{id} ")
    List<Cover> findById(Integer id);

    /**
     * 根据类型id和井盖编号查询
     * @param genreId
     * @param code
     * @return
     */
    @Select(value = "SELECT id,genreId,genreName,number,unitName,status,title,imgUrl,equipmentCode,insertTime,temperature,measuredNumber FROM zrzk_cover WHERE genreId=#{genreId} and equipmentCode=#{code}")
    List<Cover> findByGenreIdAndCode(@Param("genreId") Integer genreId,@Param("code")String code);

    /**
     * 根据井盖编号查询
     * @param code
     * @return
     */
    @Select(value = "SELECT id,genreId,genreName,number,unitName,status,title,imgUrl,equipmentCode,insertTime,temperature,measuredNumber FROM zrzk_cover WHERE equipmentCode=#{code}")
    List<Cover> findByCode(String code);
}
