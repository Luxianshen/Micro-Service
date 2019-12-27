package com.github.lujs.auth.api.model.Role;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/22 11:43
 */
@Data
public class RoleQuery {

    /**
     * 搜索用 start
     */
    private String roleName;

    private String roleCode;
    /**
     * 搜索用 end
     */

    private String agentId;

    private List<String> roles;
}
