package com.github.lujs.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 键值枚举
 * @author binchao
 */
public interface ValueEnum {

    /**
     * 状态码
     * @return code
     */
    @JsonValue
    Integer code();

    /**
     * 状态值
     * @return value
     */
    String text();
}
