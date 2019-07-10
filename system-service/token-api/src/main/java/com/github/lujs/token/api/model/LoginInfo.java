package com.github.lujs.token.api.model;

import lombok.Data;

/**
 * @Description: 登陆实体
 * @Author lujs
 * @Date 2019/7/10 13:59
 */
@Data
public class LoginInfo {

    //账号
    private String userName;
    //密码
    private String passWord;
    //验证码
    private String validCode;
    //短信验证码
    private String msgValidCode;
    //登陆类型
    private Integer loginType;
}
