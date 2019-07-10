package com.github.lujs.annotation;

/**
 * @author: lujs
 * @date: 2019-01-30 17:12
 * @desc:
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
