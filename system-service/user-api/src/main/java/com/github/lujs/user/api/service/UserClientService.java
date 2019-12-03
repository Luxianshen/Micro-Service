package com.github.lujs.user.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lujs.user.api.model.UserClient;

/**
 * @Description: 用户服务
 * @Author: lujs
 * @Data: 2019/5/1 11:08
 * @version: 1.0.0
 */

public interface UserClientService extends IService<UserClient> {

    UserClient getClientByAgentId(String agentId);

    boolean saveClient(UserClient data);
}
