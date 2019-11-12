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
     * 根据Id寻找用户角色
     * @param userId 用户ID
     * @return 用户角色
     */
    List<String> getUserRoleList(String userId);
}
