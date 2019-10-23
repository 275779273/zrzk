package com.zrzk.controller;

import com.zrzk.pojo.Region;
import com.zrzk.pojo.Result;
import com.zrzk.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @RequestMapping("/update")
    public Result update(Region region) {
        Integer Integer = regionService.update(region);
        if (Integer > 0) {
            return new Result(true, "修改数据成功");
        } else {
            return new Result(false, "修改数据失败");
        }
    }

    @RequestMapping("/findByGenreId/{genreId}")
    public Region findByGenreId(@PathVariable Integer genreId){
        Region region = regionService.findByGenreId(genreId);
        return region;
    }
}
