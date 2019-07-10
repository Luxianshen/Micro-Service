package com.github.lujs.auth.apiimpl.service;

import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.auth.apiimpl.mapper.RoleMapper;
import com.github.lujs.service.CrudService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: lujs
 * @Data: 2019/5/18 10:56
 * @version: 1.0.0
 */
@Service
public class RoleService extends CrudService<RoleMapper, Role> {

}
