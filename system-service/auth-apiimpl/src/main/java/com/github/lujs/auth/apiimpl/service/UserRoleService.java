package com.github.lujs.auth.apiimpl.service;

import java.util.Set;

/**
 * @Description: 用户角色关系服务
 * @Author: lujs
 * @Data: 2019/5/18 18:23
 * @version: 1.0.0
 */
public interface UserRoleService {

    /**
     * 根据用户名寻找用户角色
     * @param username
     * @return
     */
    Set<String> getUserRoleList(String username);
}
