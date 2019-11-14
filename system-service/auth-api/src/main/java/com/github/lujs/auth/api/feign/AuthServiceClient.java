package com.github.lujs.auth.api.feign;

import com.github.lujs.auth.api.feign.hystrix.AuthServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Description: 用户权限服务
 * @Author lujs
 * @Date 2019/11/13 15:17
 */
@FeignClient(name = "auth-service",fallback = AuthServiceHystrix.class)
public interface AuthServiceClient {

    /**
     * 获取用户角色列表
     */
    @GetMapping("/v1/role/getUserRoleList")
    List<String> getUserRoleList(String userId);

    /**
     * 获取用户权限列表
     */
    @GetMapping("/v1/menu/getUserPermissionList")
    List<String> getUserPermissionList(List<String> roles);
}
