package com.github.lujs.auth.api.model.UserRole;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

/**
 * @Description: 用户角色关系实体
 * @Author: lujs
 * @Data: 2019/5/18 18:25
 * @version: 1.0.0
 */
@Data
@TableName("tb_user_role_rel")
public class UserRole extends BaseEntity {

    private String userId;

    private String roleId;

}
