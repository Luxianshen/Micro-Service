package com.github.lujs.common;

import java.util.UUID;

/**
 * @Describe: uuid生成工具类
 * @Author: lujs
 * @Date: 2019/4/29 12:58
 * @Version: 1.0.0
 **/
public class IdGen {

    /**
     * 封装JDK自带的UUID, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
