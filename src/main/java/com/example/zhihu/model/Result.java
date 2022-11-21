package com.example.zhihu.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


//  结果模型
public class Result<D> implements Serializable {
    @JsonProperty("isSuccess")
    private boolean success = false;

    private int symbol = 0;


    private String code;
    private String message;

    private D data;

    public static <T> Result<T> create(){
        return new Result<>();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
