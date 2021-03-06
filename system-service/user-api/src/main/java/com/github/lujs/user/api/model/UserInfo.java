package com.github.lujs.user.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 用户信息实体
 * @Author lujs
 * @Date 2019/7/10 11:20
 */
@Data
public class UserInfo implements Serializable {

    private String name;

    private String agentId;

    private String phone;

    private String avatar;

    private String avatarId;

    private String email;

    private String sex;

    private String born;

    private String remark;

    private String state;

    /**
     * 用户角色列表
     */
    private List<String> roleList;
    /**
     * 用户权限列表
     */
    private List<String> permissionList;
    /**
     * 用户接口列表
     */
    private List<String> apiList;
}
