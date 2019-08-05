package com.juziwl.commonlibrary.model;

import java.io.Serializable;


/**
 * @description 统一的请求参数
 */
public class ResponseData<T> implements Serializable {
    public String code = "";
    public String message = "";
    public boolean success; //是否成功
    public T data;

    @Override
    public String toString() {
        return "ResponseData{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public ResponseData() {
    }


    public ResponseData(String code, String message, boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }
}
