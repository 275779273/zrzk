package com.zrzk.dao;

import com.zrzk.pojo.Genre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface GenreDao {
    @Results(id = "genreMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "genreName",column = "genre_name")
    })
    @Select(value = "SELECT id,genre_name FROM genre ")
    List<Genre> findAll();
}
