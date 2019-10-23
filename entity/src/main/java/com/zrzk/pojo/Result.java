package com.zrzk.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 返回的结果集
 */
@Data
public class Result implements Serializable {
    private Boolean success;
    private String message;
    //private List list=new ArrayList();
    private Object obj;


    public Result() {
    }

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(Boolean success, String message, Object obj) {
        this.success = success;
        this.message = message;
        this.obj = obj;
    }


}
