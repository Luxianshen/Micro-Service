package com.github.lujs.auth.apiimpl.controller;

import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.auth.api.service.RoleService;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 菜单控制层
 * @Author: lujs
 * @Data: 2019/5/18 10:25
 * @version: 1.0.0
 */
@AllArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController extends BaseController {

    public final RoleService roleService;

    public final RedisTemplate redisTemplate;

    /**
     * 获取用户权限
     */
    @PostMapping("/menu/getRolePermissionList")
    @Permission(action = Action.Skip)
    public List<String> getRolePermissionList(@RequestBody List<String> roles) {

        if (roles == null || roles.size() < 1) {
            return new ArrayList<>();
        }
        return roleService.getUserPermissionList(roles);
    }


    @Permission(action = Action.Skip)
    @RequestMapping(value = "/menu/userMenu", produces = "application/json; charset=utf-8")
    public Object testMenu() {
        return redisTemplate.opsForValue().get("test");
    }

    /**
     * 获取用户角色
     */
    @PostMapping("/role/getUserRoleList")
    @Permission(action = Action.Skip)
    public List<String> getUserRoleList(@RequestParam("userId") Long userId) {
        return roleService.getUserRoleList(userId);
    }

}
