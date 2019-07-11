package com.github.lujs.auth.apiimpl.service;

import java.util.Set;

/**
 * @Description: 角色菜单关系服务
 * @Author: lujs
 * @Data: 2019/5/18 18:24
 * @version: 1.0.0
 */
public interface RoleMenuService {

    /**
     * 根据用户名称获取用户权限列表
     * @param username
     * @return
     */
    Set<String> getUserPermissionList(String username);
}
