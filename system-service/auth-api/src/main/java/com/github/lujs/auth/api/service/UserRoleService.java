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
     * @return 角色列表
     */
    List<String> getUserRoleList(Long userId);

    /**
     * 根据Id寻找角色用户
     * @param roleId 角色ID
     * @return 用户列表
     */
    List<String> getRoleUserList(Long roleId);
}
