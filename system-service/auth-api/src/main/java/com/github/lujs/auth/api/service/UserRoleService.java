package com.github.lujs.auth.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lujs.auth.api.model.UserRole.UserRole;

import java.util.List;

/**
 * @Description: 用户角色关系服务
 * @Author: lujs
 * @Data: 2019/5/18 18:23
 * @version: 1.0.0
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 根据用户名寻找用户角色
     * @param username
     * @return
     */
    List<String> getUserRoleList(String username);
}
