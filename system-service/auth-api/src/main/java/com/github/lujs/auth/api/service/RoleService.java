package com.github.lujs.auth.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lujs.auth.api.model.Role.*;

import java.util.List;

/**
 * @Description: 角色服务类
 * @Author: lujs
 * @Data: 2019/5/18 10:56
 * @version: 1.0.0
 */
public interface RoleService extends IService<Role> {

    /**
     * 获取用户角色
     * @param userId 代理ID
     * @return 角色IDs
     */
    List<String> getUserRoleList(Long userId);

    /**
     *
     *
     * @param agentId
     * @param roles
     * @return
     */
    List<String> getUserPermissionList(RoleQuery roleQuery);

    /**
     * @param page
     * @param param
     * @return
     */
    IPage<RoleDto> authUserPage(IPage<RoleDto> page, RoleDto param);

    /**
     *
     * @return
     */
    List<VOrgTree> findRolePermissionTree(RolePermissionQuery rolePermissionQuery);

    List<String> getRoleUserList(Long roleId);
}
