package com.github.lujs.auth.api.model.UserRole;

import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

/**
 * @Description: 用户角色关系实体
 * @Author: lujs
 * @Data: 2019/5/18 18:25
 * @version: 1.0.0
 */
@Data
public class UserRole extends BaseEntity<UserRole> {

    private String userId;

    private String roleId;

}
