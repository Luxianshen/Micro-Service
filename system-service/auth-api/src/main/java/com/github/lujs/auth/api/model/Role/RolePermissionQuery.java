package com.github.lujs.auth.api.model.Role;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: binchao
 * @date: 2019/2/12
 * @desc:
 */
@Data
public class RolePermissionQuery implements Serializable {

    /**
     * 角色id
     */
    @NotNull(message = "角色id必填")
    private Long roleId;

    /**
     * 上级权限id
     */
    private Long pid;

    /**
     * 权限类型
     */
    @NotNull(message = "权限类型必填")
    private Integer type;
}
