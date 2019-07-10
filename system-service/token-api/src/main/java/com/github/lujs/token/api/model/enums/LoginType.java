package com.github.lujs.token.api.model.enums;



/**
 * 登录类型枚举
 * @author binchao
 */
public enum LoginType {

    NORMAL(0, "用户名登录"),
    PHONE_NO(1, "短信验证码登录");


    private Integer code;

    private String text;

    LoginType(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer code() {
        return code;
    }

    public String text() {
        return text;
    }


    @Override
    public String toString() {
        return this.code.toString();
    }}
