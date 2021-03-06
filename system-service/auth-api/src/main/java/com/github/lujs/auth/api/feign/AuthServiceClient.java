package com.github.lujs.auth.api.feign;

import com.github.lujs.auth.api.feign.hystrix.AuthServiceHystrix;
import com.github.lujs.auth.api.model.Role.RoleQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 用户权限服务
 * @Author lujs
 * @Date 2019/11/13 15:17
 */
@FeignClient(name = "auth-service", fallback = AuthServiceHystrix.class)
public interface AuthServiceClient {

    /**
     * 获取用户角色列表
     */
    @PostMapping("/v1/role/getUserRoleList")
    List<String> getUserRoleList(@RequestParam("userId") Long userId);

    /**
     * 获取用户权限列表
     */
    @PostMapping("/v1/menu/getRolePermissionList")
    List<String> getRolePermissionList(RoleQuery roleQuery);

    /**
     * 获取用户角色列表
     */
    @PostMapping("/v1/role/getRoleUserList")
    List<String> getRoleUserList(@RequestParam("roleId") Long roleId);
}
