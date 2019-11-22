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

    private String agentId;

    private List<String> roles;
}
