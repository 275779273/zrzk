package com.zrzk.service.impl;

import com.zrzk.dao.RegionDao;
import com.zrzk.pojo.Region;
import com.zrzk.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionDao regionDao;
    @Override
    @Transactional
    public int update(Region region) {
        return regionDao.update(region);
    }

    public Region findByGenreId(Integer genreId){
        Region byGenreId = regionDao.findByGenreId(genreId);
        return byGenreId;
    }
}
