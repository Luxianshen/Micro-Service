package com.github.lujs.auth.api.model.Role;

import com.github.lujs.persistence.BaseEntity;

/**
 * @Description: 角色实体
 * @Author: lujs
 * @Data: 2019/5/18 9:46
 * @version: 1.0.0
 */
public class Role extends BaseEntity<Role> {

    private String roleName;

    private String roleCode;

    private String roleDesc;

    private String status;


    /**
     * 是否默认 0-否，1-是
     */
    private String isDefault;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}

