package com.github.lujs.auth.api.model.RoleMenu;

import com.github.lujs.persistence.BaseEntity;

/**
 * @Description: 角色菜单关系实体
 * @Author: lujs
 * @Data: 2019/5/18 18:25
 * @version: 1.0.0
 */
public class RoleMenu extends BaseEntity<RoleMenu> {

    private String roleId;

    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
