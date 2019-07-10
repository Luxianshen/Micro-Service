package com.github.lujs.auth.apiimpl.service;

import com.github.lujs.auth.api.model.UserRole.UserRole;
import com.github.lujs.auth.apiimpl.mapper.UserRoleMapper;
import com.github.lujs.service.CrudService;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户角色关系服务
 * @Author: lujs
 * @Data: 2019/5/18 18:23
 * @version: 1.0.0
 */
@Service
public class UserRoleService extends CrudService<UserRoleMapper, UserRole> {
}
