package com.github.lujs.auth.apiimpl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.auth.api.model.Role.RoleDto;
import com.github.lujs.auth.api.model.Role.VOrgTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 角色mapper
 * @Author: lujs
 * @Data: 2019/5/1810:53
 * @version: 1.0.0
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    IPage<RoleDto> findAuthUser(IPage<RoleDto> page,@Param("param") RoleDto param);

    List<VOrgTree> findMenuPermissionTree(VOrgTree tree,@Param("roleId") Long roleId,@Param("pid") Long pid);

    List<VOrgTree> findApiPermissionTree(VOrgTree tree,@Param("roleId") Long roleId,@Param("pid") Long pid);
}
