package com.github.lujs.auth.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lujs.auth.api.model.RoleMenu.RoleMenu;

import java.util.Collection;
import java.util.List;

/**
 * @Description: 角色菜单关系服务
 * @Author: lujs
 * @Data: 2019/5/18 18:24
 * @version: 1.0.0
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     *
     * @param roleId
     * @return
     */
    List<String> getRoleMenuCodes(String roleId);
}
