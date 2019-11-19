package com.github.lujs.auth.apiimpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.api.model.RoleMenu.RoleMenu;
import com.github.lujs.auth.api.service.MenuService;
import com.github.lujs.auth.api.service.RoleMenuService;
import com.github.lujs.auth.apiimpl.mapper.RoleMenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 角色菜单服务实现类
 * @Author lujs
 * @Date 2019/7/11 11:35
 */
@AllArgsConstructor
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {


    @Override
    public List<String> getRoleMenuCodes(String roleId) {
        return baseMapper.getRoleMenuCodes(roleId);
    }
}
