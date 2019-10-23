package com.zrzk.controller;

import com.zrzk.pojo.Genre;
import com.zrzk.pojo.Result;
import com.zrzk.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//类型表
@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    /**
     * 查询所有设备种类
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll(){
        List<Genre> genreList = genreService.findAll();
        if(genreList!=null&&genreList.size()>0){
            return new Result(true,"查询到数据",genreList);
        }else {
            return new Result(false,"未查到数据");
        }
    }
}
