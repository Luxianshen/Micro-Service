package com.github.lujs.auth.apiimpl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lujs.auth.api.model.RoleMenu.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 角色菜单mapper
 * @Author: lujs
 * @Data: 2019/5/18 18:33
 * @version: 1.0.0
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<String> getRoleMenuCodes(@Param("roleId") String roleId);
}
