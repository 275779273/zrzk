package com.zrzk.service;



import com.zrzk.pojo.Cover;

import java.util.List;

public interface CoverService {
    /**
     * 查询所有井盖信息
     * @return
     */
    List findAll();

    /**
     * 添加数据
     * @param cover
     */
    Integer save(Cover cover);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    List findById(Integer id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

    /**
     * 删除所有数据
     * @return
     */
    Integer deleteAll();

    Long getCount();

    List findByGenreId(Integer genreId);

    List<Cover> findByGenreIdAndCode(Integer genreId,String code);

    List<Cover> findByCode(String code);

}
