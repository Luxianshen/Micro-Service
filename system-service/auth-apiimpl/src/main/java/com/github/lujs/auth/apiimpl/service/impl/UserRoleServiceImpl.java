package com.github.lujs.auth.apiimpl.service.impl;

import com.github.lujs.auth.api.model.UserRole.UserRole;
import com.github.lujs.auth.apiimpl.mapper.UserRoleMapper;
import com.github.lujs.auth.apiimpl.service.UserRoleService;
import com.github.lujs.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户角色权限类
 * @Author lujs
 * @Date 2019/7/11 11:37
 */
@Service
public class UserRoleServiceImpl  extends CrudService<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<String> getUserRoleList(String username) {
        return new ArrayList<>(); //todo
    }
}
