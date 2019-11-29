package com.github.lujs.user.api.feign;

import com.github.lujs.user.api.feign.hystrix.UserServiceHystrix;
import com.github.lujs.user.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 用户服务
 * @Author lujs
 * @Date 2019/11/13 15:17
 */
@FeignClient(name = "user-service",fallback = UserServiceHystrix.class)
public interface UserServiceClient {

    /**
     * 根据用户名获取用户信息
     */
    @PostMapping("/v1/user/checkUserLoginInfo")
    User checkUserLoginInfo(@RequestParam("agentId") String agentId,@RequestParam("agentAuth") String agentAuth);

    /**
     * 根据用户名获取用户信息
     */
    @PostMapping("/v1/user/getUserAgentIds")
    List<String> getUserAgentIds(List<String> userList);
}
