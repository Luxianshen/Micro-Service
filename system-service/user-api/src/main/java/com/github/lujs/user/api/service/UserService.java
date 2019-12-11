package com.github.lujs.user.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lujs.user.api.model.User;

/**
 * @Description: 用户服务
 * @Author: lujs
 * @Data: 2019/5/1 11:08
 * @version: 1.0.0
 */

public interface UserService extends IService<User> {

    User getUserByAgentId(String agentId);

    User checkUserLoginInfo(String agentId,String agentAuth);

    /**
     * 注册用户
     * @param data
     * @return
     */
    boolean register(User data);
}
