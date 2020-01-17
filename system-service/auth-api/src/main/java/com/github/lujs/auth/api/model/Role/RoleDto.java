package com.github.lujs.auth.api.model.Role;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lujs.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/21 10:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto extends BaseEntity {

    private String agentId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    private Integer state;

    /**
     * 用户姓名
     */
    private String name;

    private Boolean checked;
}
