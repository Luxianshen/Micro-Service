package com.github.lujs.user.api.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户信息更新vo
 *
 * @author: Lujs
 * @date: 2019-01-31 15:16
 * @desc:
 */
@Data
public class UserUpdateRequest implements Serializable {

    /**
     * id
     */
    @NotNull
    private Long id;

    /**
     * 用户帐号
     */
    @NotEmpty(message = "用户名必填")
    private String agentId;

    /**
     * 用户状态
     */
    @NotNull(message = "用户状态必填")
    private Integer state;

    /**
     * 用户姓名
     */
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式不合法")
    private String email;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户性别
     */
    private Integer gender;

    /**
     * 用户手机号
     */
    //@Pattern(regexp = RegUtil.REG_MOBILE, message = "手机号码格式不合法")
    private String phoneNo;
}
