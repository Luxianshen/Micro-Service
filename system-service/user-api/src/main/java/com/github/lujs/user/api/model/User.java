package com.github.lujs.user.api.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.lujs.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Describe: 用户实体
 * @Author: lujs
 * @Date: 2019/4/29 10:46
 * @Version: 1.0.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_user")
public class User extends BaseEntity {

    @NotEmpty(message = "用户名必填")
    @Size(min = 3, max = 30)
    private String agentId;

    /**
     * 加密密码
     */
    @JsonIgnore
    private byte[] agentAuth;

    /**
     * 加密盐
     */
    @JsonIgnore
    private byte[] salt;

    /**
     * 用户状态
     */
    private Integer state;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 用户性别
     */
    private Integer gender;

    /**
     * 用户电话号码
     */
    private String phoneNo;

    @TableField(exist = false)
    private String tmpAuth;
}
