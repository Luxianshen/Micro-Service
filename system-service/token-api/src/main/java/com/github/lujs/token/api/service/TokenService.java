package com.github.lujs.token.api.service;

import com.github.lujs.token.api.model.LoginInfo;
import com.github.lujs.user.api.model.UserClient;

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
    String login(LoginInfo loginInfo);

    /**
     * 生成客户端token
     * @param userClient 客户端
     * @return token
     */
    String generateClientToken(UserClient userClient);
}
