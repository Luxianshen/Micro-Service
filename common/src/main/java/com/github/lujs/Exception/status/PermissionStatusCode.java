package com.github.lujs.Exception.status;


import com.github.lujs.model.enums.ValueEnum;

/**
 * 权限状态码列表
 * 错误码范围
 * @author lujs
 */
public enum PermissionStatusCode implements ValueEnum {

    /**
     * 权限状态码
     */
    NO_PERMISSION(401, "无权访问"),
    TOKEN_OVERDUE(10900, "TOKEN过期"),
    TOKEN_ILLEGAL(10900, "TOKEN不合法"),
    NO_TOKEN(10901, "缺失TOKEN");


    private Integer code;

    private String text;


    PermissionStatusCode(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String text() {
        return text;
    }
}
