package com.zrzk.service.impl;

import com.zrzk.dao.LogDao;
import com.zrzk.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    public Integer save(String logStr) {
        Integer integer = logDao.save(logStr);
        return integer;
    }
}
