package com.github.lujs.auth.api.model.Role;

import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

/**
 * @Description: 角色实体
 * @Author: lujs
 * @Data: 2019/5/18 9:46
 * @version: 1.0.0
 */
@Data
public class Role extends BaseEntity<Role> {

    private String roleName;

    private String roleCode;

    private String roleDesc;

    private String status;


    /**
     * 是否默认 0-否，1-是
     */
    private String isDefault;

}

