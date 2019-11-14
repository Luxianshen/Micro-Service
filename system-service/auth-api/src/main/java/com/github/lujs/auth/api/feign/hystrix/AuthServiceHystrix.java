package com.github.lujs.auth.api.feign.hystrix;

import com.github.lujs.auth.api.feign.AuthServiceClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户权限服务熔断
 * @Author lujs
 * @Date 2019/11/13 15:20
 */
@Component
public class AuthServiceHystrix implements AuthServiceClient {


    @Override
    public List<String> getUserRoleList(String userId) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getUserPermissionList(List<String> roles) {
        return new ArrayList<>();
    }
}
