package com.github.lujs.auth.api.model.Role;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lujs.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 角色实体
 * @Author: lujs
 * @Data: 2019/5/18 9:46
 * @version: 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_role")
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 所属系统ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sysId;

    /**
     * 是否默认角色
     */
    private Integer defaultRole;

    /**
     * 角色编码
     */
    private String roleCode;

}

