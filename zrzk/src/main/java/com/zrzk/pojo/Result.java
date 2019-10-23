package com.zrzk.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回的结果集
 */
public class Result {
    private Boolean success;
    private String message;
    private List list=new ArrayList();


    public Result() {
    }

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(Boolean success, String message, List list) {
        this.success = success;
        this.message = message;
        this.list = list;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", list=" + list +
                '}';
    }
}
