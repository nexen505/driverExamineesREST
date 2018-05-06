package com.komarmoss.model.vo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WebResponseVO<T> implements Serializable {
    public static final WebResponseVO SUCCESS = new WebResponseVO();
    public static final WebResponseVO EMPTY_LIST = new WebResponseVO(Collections.emptyList());

    private boolean success;
    private Serializable msg;
    private int total;
    private Object result;

    public WebResponseVO() {
        success = true;
    }

    public WebResponseVO(String s) {
        this.success = true;
        this.msg = s;
    }

    public WebResponseVO(Boolean value) {
        this.success = true;
        this.result = value;
    }

    public WebResponseVO(boolean success, Serializable msg) {
        this.success = success;
        this.msg = msg;
    }

    public WebResponseVO(List result) {
        this.success = true;
        this.total = result.size();
        this.result = result.toArray();
    }

    public WebResponseVO(int total, List result) {
        this.success = true;
        this.total = total;
        this.result = result.toArray();
    }

    public WebResponseVO(int total, List result, Serializable msg) {
        this.success = true;
        this.total = total;
        this.result = result.toArray();
        this.msg = msg;
    }

    public WebResponseVO(Object result) {
        this.success = true;
        this.result = result;
    }

    public WebResponseVO(Map map) {
        this.success = true;
        this.result = (Serializable) map;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Serializable getMsg() {
        return msg;
    }

    public void setMsg(Serializable msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getResult() {
        return (T) result;
    }

    public void setResult(T result) {
        this.result = result;
    }


}
