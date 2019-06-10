package com.github.lujs.auth.apiimpl.mapper;

import com.github.lujs.auth.api.model.RoleMenu.RoleMenu;
import com.github.lujs.persistence.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 角色菜单mapper
 * @Author: lujs
 * @Data: 2019/5/18 18:33
 * @version: 1.0.0
 */
@Mapper
public interface RoleMenuMapper extends CrudMapper<RoleMenu> {

}
