package com.zrzk.service.impl;

import com.zrzk.dao.GenreDao;
import com.zrzk.pojo.Genre;
import com.zrzk.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreDao genreDao;
    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }
}
