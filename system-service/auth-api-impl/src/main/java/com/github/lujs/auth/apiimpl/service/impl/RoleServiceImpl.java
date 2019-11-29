package com.github.lujs.auth.apiimpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.api.model.Role.*;
import com.github.lujs.auth.api.service.MenuService;
import com.github.lujs.auth.api.service.RoleMenuService;
import com.github.lujs.auth.api.service.UserRoleService;
import com.github.lujs.auth.apiimpl.mapper.RoleMapper;
import com.github.lujs.auth.api.service.RoleService;
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
    public List<String> getUserPermissionList(RoleQuery roleQuery) {
        //先获取角色菜单关系
        Set<String> menuIds = new HashSet<>();
        roleQuery.getRoles().forEach(x -> menuIds.addAll(roleMenuService.getRoleMenuCodes(x)));
        if (menuIds.size() > 0) {
            //获取菜单
            QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
            menuQueryWrapper.in("id", menuIds);
            List<Menu> menuList = menuService.list(menuQueryWrapper);
            //把菜单设置进缓存
            redisTemplate.opsForValue().set(roleQuery.getAgentId()+"Menu", getMenuTreeList(menuList,null));
            //获取菜单权限作为角色权限
            return menuList.stream().map(Menu::getPermissionCode).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public IPage<RoleDto> authUserPage(IPage<RoleDto> page, RoleDto param) {
        return baseMapper.findAuthUser(page,param);
    }

    @Override
    public List<VOrgTree> findRolePermissionTree(RolePermissionQuery query) {
        return findChildren(null, query.getType(), query.getRoleId(), null);
    }

    @Override
    public List<String> getRoleUserList(Long roleId) {
        List<String> users = userRoleService.getRoleUserList(roleId);
        if (users != null) {
            return users;
        }
        return new ArrayList<>();
    }


    /**
     * 查询所有菜单树
     * JDK1.8
     */
    private List<Menu> getMenuTreeList(List<Menu> menuList,Long pid) {
        // 查找所有菜单
        List<Menu> childrenList = new ArrayList<>();
        menuList.stream()
                .filter(d -> Objects.equals(pid, d.getPid()))
                .collect(Collectors.toList())
                .forEach(d -> {
                    d.setChildren(getMenuTreeList(menuList,d.getId()));
                    childrenList.add(d);
                });
        return childrenList;
    }

    private List<VOrgTree> findChildren(VOrgTree tree, Integer type, Long roleId, Long pid) {
        List<VOrgTree> children;
        if(type.equals(0)){
             children = baseMapper.findMenuPermissionTree(tree,roleId,pid);
        }else {
             children = baseMapper.findApiPermissionTree(tree,roleId,pid);
        }
        for (VOrgTree t : children) {
            findChildren(t, type, roleId, t.getId());
        }
        if (null != tree) {
            tree.setChildren(children);
        } else if (null == tree) {
            return children;
        }
        return null;
    }

}
