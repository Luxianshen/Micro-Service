package com.github.lujs.auth.api.model.RoleMenu;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: 角色菜单关系实体
 * @Author: lujs
 * @Data: 2019/5/18 18:25
 * @version: 1.0.0
 */
@Data
@TableName("tb_role_menu_rel")
public class RoleMenu extends BaseEntity {

    @NotNull(
            message = "roleId必填"
    )
    private String roleId;

    @NotNull(
            message = "menuId必填"
    )
    private String menuId;

}
