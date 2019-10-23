package com.task.dao;


import com.task.pojo.Cover;
import org.apache.ibatis.annotations.*;

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
     * 添加
     *
     * @param cover
     */
    @Insert(value = " INSERT INTO zrzk_cover (genreId , genreName , number , unitName , STATUS , title , imgUrl , equipmentCode , insertTime,temperature,measuredNumber) " +
            "VALUES(#{genreId},#{genreName},#{number},#{unitName},#{status},#{title},#{imgUrl},#{equipmentCode},#{insertTime},#{temperature},#{measuredNumber}) ")
    Integer save(Cover cover);

    @Select(value = "select count(1) from zrzk_cover where genreId=#{genreId} ")
    Long getCount(Integer genreId);

    @Select(value = "select equipmentCode from zrzk_cover where genreId=#{genreId} GROUP BY equipmentCode")
    List<String> getCode(Integer genreId);
}
