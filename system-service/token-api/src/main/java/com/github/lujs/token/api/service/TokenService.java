package com.github.lujs.token.api.service;

import com.github.lujs.token.api.model.LoginInfo;

/**
 * @Description: token服务
 * @Author lujs
 * @Date 2019/7/10 13:50
 */
public interface TokenService {

    /**
     * 用户登陆
     * @param loginInfo 登陆信息
     * @return token
     */
    public String login(LoginInfo loginInfo);
}
