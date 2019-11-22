package com.github.lujs.auth.api.model.Role;

import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/21 10:46
 */
@Data
public class RoleDto extends BaseEntity {

    private String agentId;

    /**
     * 用户姓名
     */
    private String name;

    private Boolean checked;
}
