package com.github.lujs.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.model.enums.ValueEnum;

import java.io.Serializable;

/**
 * 标准响应报文
 * @author Lujs
 */
@JsonPropertyOrder({"code", "msg", "data"})
public class BaseResponse<T> implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    public BaseResponse() {
        this.setSuccess();
    }

    public BaseResponse(String msg) {
        this.msg = msg;
    }

    public BaseResponse(ValueEnum statusCode) {
        this.setStatus(statusCode);
    }

    public BaseResponse(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setFailed() {
        this.setStatus(GlobalStatusCode.FAILED);
    }
    public void setSuccess() {
        this.setStatus(GlobalStatusCode.SUCCESS);
    }

    public void setStatus(ValueEnum status) {
        this.code = status.code();
        this.msg = status.text();
    }
}
