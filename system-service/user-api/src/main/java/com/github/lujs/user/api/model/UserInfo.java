package com.github.lujs.user.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description: 用户信息实体
 * @Author lujs
 * @Date 2019/7/10 11:20
 */
@Data
public class UserInfo extends User implements Serializable {

    //用户角色列表
    private Set<String> roleList;
    //用户权限列表
    private Set<String> permissionList;
}
