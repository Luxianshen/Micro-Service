package com.github.lujs.constant;

/**
 * @Describe: 公用常量
 * @Author: lujs
 * @Date: 2019/4/29 12:56
 * @Version: 1.0.0
 **/
public class CommonConstant {

    //todo
    /**
     * 默认系统编号
     */
    public static final String SYS_CODE = "Platform";

    /**
     * 用户token前缀
     */
    public static final String TOKEN_CODE = SYS_CODE + "-U-Token";

    /**
     * client用户token前缀
     */
    public static final String API_TOKEN_CODE = SYS_CODE + "-Api-Token";

    /**
     * 请求转发id地址
     */
    public static final String REQUEST_ID_HEADER = "x-user-id";

    /**
     * 请求转发user地址
     */
    public static final String REQUEST_NAME_HEADER = "x-user-name";

    /**
     * 请求转发host地址
     */
    public static final String REQUEST_HOST_HEADER = "x-user-host";
}
