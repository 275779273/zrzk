package com.zrzk_mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.Mapper;

public interface BaseMapper<T> extends Mapper<T> {
    int insert(T entity);
}
