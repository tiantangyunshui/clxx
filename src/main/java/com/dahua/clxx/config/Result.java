package com.dahua.clxx.config;

import lombok.Data;

@Data
public class Result<T> {
    public T data;
    public String msg = "success";
    public int code = 200;

    public Result(T data) {
        this.data = data;
    }

    public Result(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }


}
