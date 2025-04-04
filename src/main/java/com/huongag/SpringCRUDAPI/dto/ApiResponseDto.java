package com.huongag.SpringCRUDAPI.dto;

import com.huongag.SpringCRUDAPI.utils.Meta;

public class ApiResponseDto<T> {
    private  int statusCode;
    private String message;
    private Meta meta;
    private T data;

    public ApiResponseDto() {
    }

    public ApiResponseDto(int statusCode, String message, Meta meta, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.meta = meta;
        this.data = data;
    }

    public ApiResponseDto(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
