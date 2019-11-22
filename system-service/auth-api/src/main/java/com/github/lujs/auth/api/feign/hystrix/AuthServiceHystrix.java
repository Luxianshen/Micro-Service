package com.github.lujs.auth.api.feign.hystrix;

import com.github.lujs.auth.api.feign.AuthServiceClient;
import com.github.lujs.auth.api.model.Role.RoleQuery;
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
    public List<String> getUserRoleList(Long userId) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getRolePermissionList(RoleQuery roleQuery) {
        return new ArrayList<>();
    }
}
