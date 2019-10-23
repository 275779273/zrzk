package com.zrzk.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogDao {

    @Insert(value = "insert into zrzk_log (logStr) values(#{logStr}) ")
    public Integer save(String logStr);
}
