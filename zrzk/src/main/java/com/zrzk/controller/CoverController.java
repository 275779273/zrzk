package com.zrzk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zrzk.pojo.Cover;
import com.zrzk.pojo.Result;
import com.zrzk.service.impl.CoverServiceImpl;

import java.util.List;

//井盖
@RestController
@RequestMapping("/cover")
public class CoverController {
    @Autowired
    private CoverServiceImpl coverService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAllCover(){
        List coverList = coverService.findAll();
        if(coverList!=null&&coverList.size()>0){
            return new Result(true,"查询到数据",coverList);
        }else {
            return new Result(false,"未查到数据");
        }
    }

    /**
     * 添加
     * @param cover
     * @return
     */
    @RequestMapping("/save")
    public Result saveCover(Cover cover){
        Integer integer = coverService.save(cover);
        if(integer>0){
            return new Result(true,"添加数据成功");
        }else {
            return new Result(false,"添加数据失败");
        }
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping("/findById/{id}")
    public Result findCoverById(@PathVariable Integer id){
        List coverList = coverService.findById(id);
        if(coverList!=null&&coverList.size()>0){
            return new Result(true,"查询到数据",coverList);
        }else {
            return new Result(false,"未查询到数据");
        }
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteById/{id}")
    public Result deleteCoverById(@PathVariable Integer id){
        Integer integer = coverService.deleteById(id);
        if(integer>0){
            return new Result(true,"删除数据成功");
        }else {
            return new Result(false,"删除数据失败");
        }
    }

    /**
     * 删除所有数据
     * @return
     */
    @RequestMapping("/deleteAll")
    public Result deleteAll(){
        Integer integer = coverService.deleteAll();
        if(integer>0){
            return new Result(true,"删除数据成功");
        }else {
            return new Result(false,"删除数据失败");
        }
    }

    /**
     * 根据类型id查询此类型所有最新的数据
     * @return
     */
    @RequestMapping("/findByGenreId/{genreId}")
    public Result findByGenreId(@PathVariable Integer genreId){
        List coverList = coverService.findByGenreId(genreId);
        if(coverList!=null&&coverList.size()>0){
            return new Result(true,"查询到数据",coverList);
        }else {
            return new Result(false,"未查询到数据");
        }
    }

    /**
     * 根据类型id和井盖编号查询
     * @param genreId
     * @param code
     * @return
     */
    @RequestMapping("/findByGenreIdAndCode/{genreId}/{code}")
    public Result findByGenreIdAndCode(@PathVariable Integer genreId,@PathVariable String code){
        List<Cover> coverList = coverService.findByGenreIdAndCode(genreId, code);
        if(coverList!=null&&coverList.size()>0){
            return new Result(true,"查询到数据",coverList);
        }else {
            return new Result(false,"未查询到数据");
        }
    }

    /**
     * 根据井盖编号查询
     * @param code
     * @return
     */
    @RequestMapping("/findByCode/{code}")
    public Result findByCode(@PathVariable String code){
        List<Cover> coverList = coverService.findByCode(code);
        if(coverList!=null&&coverList.size()>0){
            return new Result(true,"查询到数据",coverList);
        }else {
            return new Result(false,"未查询到数据");
        }
    }


}
