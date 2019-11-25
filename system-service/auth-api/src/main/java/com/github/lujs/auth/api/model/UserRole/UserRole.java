package com.github.lujs.auth.api.model.UserRole;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: 用户角色关系实体
 * @Author: lujs
 * @Data: 2019/5/18 18:25
 * @version: 1.0.0
 */
@Data
@TableName("tb_user_role_rel")
public class UserRole extends BaseEntity {

    @NotNull(
            message = "userId"
    )
    private Long userId;

    @NotNull(
            message = "roleId必填"
    )
    private Long roleId;

}
