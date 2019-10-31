package com.zrzk.rms.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回的结果集
 */
@Data
public class Result implements Serializable {
    private Boolean success;
    private String message;
    //private List list=new ArrayList();
    private Object obj;

    private String token;

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

    public Result(Boolean success, String message, Object obj, String token) {
        this.success = success;
        this.message = message;
        this.obj = obj;
        this.token = token;
    }
}
