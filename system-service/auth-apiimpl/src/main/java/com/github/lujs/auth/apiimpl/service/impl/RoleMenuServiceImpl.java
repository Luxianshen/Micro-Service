package com.github.lujs.auth.apiimpl.service.impl;

import com.github.lujs.auth.api.model.RoleMenu.RoleMenu;
import com.github.lujs.auth.apiimpl.mapper.RoleMenuMapper;
import com.github.lujs.auth.apiimpl.service.RoleMenuService;
import com.github.lujs.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 角色菜单服务实现类
 * @Author lujs
 * @Date 2019/7/11 11:35
 */
@Service
public class RoleMenuServiceImpl extends CrudService<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public Set<String> getUserPermissionList(String username) {
        return new HashSet<>(); //todo
    }
}