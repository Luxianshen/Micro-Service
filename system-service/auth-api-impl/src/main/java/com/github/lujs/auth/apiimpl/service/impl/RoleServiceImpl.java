package com.github.lujs.auth.apiimpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.auth.api.service.MenuService;
import com.github.lujs.auth.api.service.RoleMenuService;
import com.github.lujs.auth.api.service.UserRoleService;
import com.github.lujs.auth.apiimpl.mapper.RoleMapper;
import com.github.lujs.auth.api.service.RoleService;
import com.github.lujs.auth.apiimpl.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 角色服务实现类
 * @Author lujs
 * @Date 2019/7/11 11:36
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取用户角色
     *
     * @param userId 代理ID
     * @return 角色IDs
     */
    @Override
    public List<String> getUserRoleList(Long userId) {

        List<String> roles = userRoleService.getUserRoleList(userId);
        if (roles != null) {
            return roles;
        }
        return new ArrayList<>();
    }

    /**
     * @param roles
     * @return
     */
    @Override
    public List<String> getUserPermissionList(List<String> roles) {
        //先获取角色菜单关系
        Set<String> menuIds = new HashSet<>();
        roles.forEach(x -> menuIds.addAll(roleMenuService.getRoleMenuCodes(x)));
        if (menuIds.size() > 0) {
            //获取菜单
            QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
            menuQueryWrapper.in("id", menuIds);
            List<Menu> menuList = menuService.list(menuQueryWrapper);
            //把菜单设置进缓存
            redisTemplate.opsForValue().set("test", menuList);
            //获取菜单权限作为角色权限
            return menuList.stream().map(Menu::getPermissionCode).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
