package com.zrzk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zrzk.dao.CoverDao;
import com.zrzk.pojo.Cover;
import com.zrzk.service.CoverService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CoverServiceImpl implements CoverService {

    @Autowired
    private CoverDao coverDao;

    public List findAll() {
        return coverDao.findAll();
    }

    @Override
    @Transactional
    public Integer save(Cover cover) {
        cover.setInsertTime(new Date());
        return coverDao.save(cover);
    }

    @Override
    public List findById(Integer id) {
        return coverDao.findById(id);
    }

    @Override
    @Transactional
    public Integer deleteById(Integer id) {
        return coverDao.deleteById(id);
    }

    @Override
    @Transactional
    public Integer deleteAll() {
        return coverDao.deleteAll();
    }

    @Override
    public Long getCount() {
        return coverDao.getCount();
    }

    @Override
    public List findByGenreId(Integer genreId) {
        if(genreId==2){
            genreId=130;
            List<Cover> coverList = coverDao.findByGenreId(genreId, "518120108");
            for (Cover cover : coverList) {
                cover.setGenreName("开盖报警器");
            }
            return coverList;
        }
        if(genreId==1){
            genreId=20;
            List<Cover> coverList = coverDao.findByGenreId1(genreId, "1805241002");
            for (Cover cover : coverList) {
                cover.setGenreName("路面积水");
            }
            return coverList;
        }
        if(genreId==3){
            genreId=20;
            List<Cover> coverList = coverDao.findByGenreId1(genreId, "419040020");
            for (Cover cover : coverList) {
                cover.setGenreName("井下液位");
                cover.setMeasuredNumber(cover.getNumber());
            }
            return coverList;
        }
        return null;
    }

    @Override
    public List<Cover> findByGenreIdAndCode(Integer genreId, String code) {
        return coverDao.findByGenreIdAndCode(genreId,code);
    }

    @Override
    public List<Cover> findByCode(String code) {
        return coverDao.findByCode(code);
    }
}
