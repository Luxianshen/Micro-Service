package com.github.lujs.auth.apiimpl.mapper;

import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.persistence.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 角色mapper
 * @Author: lujs
 * @Data: 2019/5/1810:53
 * @version: 1.0.0
 */
@Mapper
public interface RoleMapper extends CrudMapper<Role> {

}
