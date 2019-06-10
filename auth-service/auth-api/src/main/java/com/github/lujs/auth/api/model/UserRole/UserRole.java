package com.github.lujs.auth.api.model.UserRole;

import com.github.lujs.persistence.BaseEntity;

/**
 * @Description: 用户角色关系实体
 * @Author: lujs
 * @Data: 2019/5/18 18:25
 * @version: 1.0.0
 */
public class UserRole extends BaseEntity<UserRole> {

    private String userId;

    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
