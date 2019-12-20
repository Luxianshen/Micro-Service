package com.github.lujs.constant;

/**
 * @Describe: 公用常量
 * @Author: lujs
 * @Date: 2019/4/29 12:56
 * @Version: 1.0.0
 **/
public class CommonConstant {

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
     * 请求转发头部id信息
     */
    public static final String HEADER_PARAM_ID = "id";
    /**
     * 请求转发头部user信息
     */
    public static final String HEADER_PARAM_USER = "user";
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
    /**
     * 密钥
     */
    public static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
    /**
     * 认证方式
     */
    public static final String TOKEN_PREFIX = "Bearer";
    /**
     * 认证头
     */
    public static final String HEADER_AUTH = "Authorization";
    /**
     * api请求key
     */
    public static final String API_REQ = "apiKey";
}
