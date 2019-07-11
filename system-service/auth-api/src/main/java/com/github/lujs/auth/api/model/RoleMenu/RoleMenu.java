package com.github.lujs.auth.api.model.RoleMenu;

import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

/**
 * @Description: 角色菜单关系实体
 * @Author: lujs
 * @Data: 2019/5/18 18:25
 * @version: 1.0.0
 */
@Data
public class RoleMenu extends BaseEntity<RoleMenu> {

    private String roleId;

    private String menuId;

}
