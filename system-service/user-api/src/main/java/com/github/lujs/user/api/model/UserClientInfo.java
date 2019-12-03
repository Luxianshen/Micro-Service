package com.github.lujs.user.api.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 用户信息实体
 * @Author lujs
 * @Date 2019/7/10 11:20
 */
@Data
public class UserClientInfo implements Serializable {

    /**
     * 客户名
     */
    @NotNull(
            message = "客户必填"
    )
    private String agentId;

    /**
     * 客户加密密码
     */
    @NotNull(
            message = "客户认证必填"
    )
    private String agentAuth;

    /**
     * 随机串
     */
    private String randomStr;

    /**
     * 用户接口列表
     */
    private List<String> apiList;
}
