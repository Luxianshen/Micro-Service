package com.github.lujs.user.api.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: 客户请求方式
 * @Author lujs
 * @Date 2019/12/2 9:22
 */
@Data
@TableName("tb_client")
public class UserClient extends BaseEntity {

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
    private String agentAuth;

    /**
     * 客户key
     */
    private String macKey;

    /**
     * 客户端状态
     */
    private Integer state;

}
