package com.zrzk.dao;

import com.zrzk.dao.sql.RegionProvider;
import com.zrzk.pojo.Region;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RegionDao {

    /**
     * 修改上限,下限
     * @param region
     * @return
     */
    //@Update(value = "UPDATE region SET small=#{small},  big=#{big} WHERE genre_id=#{genreId};")
    @Results(id = "regionMap",value ={
            @Result(property = "genreId",column = "genre_id"),
            @Result(property = "small",column = "small"),
            @Result(property = "big",column = "big")
    } )
    @UpdateProvider(type = RegionProvider.class,method = "getUpdate")
    Integer update(Region region);

    @Insert(value = "insert into region (genre_id,small,big) values(#{genreId},#{small},#{big}) ")
    Integer save();


    /**
     * 查询正常区间
     * @param genreId
     * @return
     */
    @Results(id = "regionMap",value ={
            @Result(property = "genreId",column = "genre_id"),
            @Result(property = "small",column = "small"),
            @Result(property = "big",column = "big")
    } )
    @Select(value = "select genre_id,small,big from region where genre_id=#{genreId} ")
    Region findByGenreId(Integer genreId);

}
