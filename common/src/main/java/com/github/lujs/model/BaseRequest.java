package com.github.lujs.model;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * 标准请求报文
 * @param <T>
 * @author Lujs
 */
public class BaseRequest<T> implements Serializable {

    private Long timestamp;

    private String sign;

    @Valid
    private T data;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
