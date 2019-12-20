package com.github.lujs.constant;

import com.github.lujs.model.enums.ValueEnum;

/**
 * 全局异常状态码
 *
 * @author Lujs
 */
public enum GlobalStatusCode implements ValueEnum {

    /**
     * 系统状态码
     */
    SUCCESS(0, "成功"),

    FAILED(1, "失败"),


    USER_LOGOUT(401, "用户未登录"),

    PERMISSION_DENY(403, "127.0.0.1:8848"),

    RES_NOT_EXIST(404, "资源不存在"),

    SERVER_ERROR(500, "服务器异常"),

    INVALID_PARAMETER(90003, "参数不合法"),

    INVALID_PARAMETER_TYPE(90004, "参数类型不匹配"),

    OBJECT_IS_NOT_EXIST(90005, "对象不存在"),

    OBJECT_IS_EXIST(90006, "对象已存在"),

    MESSAGE_BODY_INVALID(90007, "报文格式不正确"),

    ERR_UNKNOWN(99999, "未知异常");


    private Integer code;

    private String text;

    GlobalStatusCode(Integer code, String text) {
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
