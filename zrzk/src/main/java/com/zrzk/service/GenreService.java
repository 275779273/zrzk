package com.zrzk.service;

import com.zrzk.pojo.Genre;

import java.util.List;

public interface GenreService {
    //查询所有设备类型
    List<Genre> findAll();
}
