package com.task.service;



import com.task.pojo.Cover;

import java.util.List;

public interface CoverService {
    /**
     * 查询所有井盖信息
     * @return
     */
    List findAll();

    /**
     * 添加数据
     *
     */
    Integer save();

    int getStatus(Integer genreId,Double number);
}
