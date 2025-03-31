package com.huongag.SpringCRUDAPI.dto;

import com.huongag.SpringCRUDAPI.utils.Meta;

public class ApiPaginateResponse<T> {
    private Meta meta;
    private T result;

    public ApiPaginateResponse() {
    }

    public ApiPaginateResponse(Meta meta, T result) {
        this.meta = meta;
        this.result = result;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
