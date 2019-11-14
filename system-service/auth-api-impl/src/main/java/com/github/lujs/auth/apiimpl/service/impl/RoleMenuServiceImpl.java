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
    public List<String> getUserPermissionList(List<String> roleList) {

        List<String> result = new ArrayList<>();
        Set<String> menuIdList = new HashSet<>();
        if (ObjectUtils.isEmpty(roleList)) {
            return result;
        }
        //去除不同角色相同的权限
        /*roleList.forEach(x -> {
            QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id", x);
            List<RoleMenu> roleMenuList = roleMenuService.list(queryWrapper);
            roleMenuList.forEach(y -> menuIdList.add(y.getMenuId()));
        });*/
        //取出权限
        /*if(ObjectUtils.isNotEmpty(menuIdList)){
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id", menuIdList);
            List<Menu> menuList = menuService.list(queryWrapper);
            //menuList.forEach(x->result.add(x.getPermission()));
        }*/
        return result;
    }
}
