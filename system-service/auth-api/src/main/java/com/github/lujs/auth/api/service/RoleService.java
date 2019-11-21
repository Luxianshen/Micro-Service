package com.github.lujs.auth.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.auth.api.model.Role.RoleDto;
import com.github.lujs.auth.api.model.Role.VOrgTree;

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
     * @param roles
     * @return
     */
    List<String> getUserPermissionList(List<String> roles);

    /**
     *
     * @param page
     * @return
     */
    IPage<RoleDto> authUserPage(IPage<RoleDto> page);

    /**
     *
     * @return
     */
    List<VOrgTree> findRolePermissionTree();
}
