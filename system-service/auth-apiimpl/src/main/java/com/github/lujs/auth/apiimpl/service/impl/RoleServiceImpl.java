package com.github.lujs.auth.apiimpl.service.impl;

import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.auth.apiimpl.mapper.RoleMapper;
import com.github.lujs.auth.apiimpl.service.RoleService;
import com.github.lujs.service.CrudService;
import org.springframework.stereotype.Service;

/**
 * @Description: 角色服务实现类
 * @Author lujs
 * @Date 2019/7/11 11:36
 */
@Service
public class RoleServiceImpl extends CrudService<RoleMapper, Role> implements RoleService {
}
