package com.github.lujs.user.api.feign.hystrix;

import com.github.lujs.user.api.feign.UserServiceClient;
import com.github.lujs.user.api.model.User;
import com.github.lujs.user.api.model.UserClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户服务熔断
 * @Author lujs
 * @Date 2019/11/13 15:20
 */
@Component
public class UserServiceHystrix implements UserServiceClient {

    @Override
    public User checkUserLoginInfo(String agentId,String agentAuth) {
        return new User();
    }

    @Override
    public List<String> getUserAgentIds(List<String> userList) {
        return new ArrayList<>();
    }

    @Override
    public boolean checkUserClient(UserClient userClient) {
        return false;
    }
}
