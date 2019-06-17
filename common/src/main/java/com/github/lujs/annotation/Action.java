package com.github.lujs.annotation;

/**
 * @Description: 权限切面 属性
 * @Author: lujs
 * @Data: 2019/6/15 9:47
 * @version: 1.0.0
 */
public enum Action {

    Normal("0", "执行权限验证"),
    Skip("1", "跳过权限验证");

    private final String key;
    private final String desc;

    private Action(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }
}
