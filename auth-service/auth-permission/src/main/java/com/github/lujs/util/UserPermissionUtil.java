package com.github.lujs.util;


import com.github.lujs.user.api.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @Describe: 用户权限验证工具
 * @Author: lujs
 * @Date: 2019/4/29 13:29
 * @Version: 1.0.0
 **/
public class UserPermissionUtil {

    //private static UserRoleService userRoleService = SpringContextHolder.getBean(UserRoleService.class);
    //private static RoleMenuService roleMenuService = SpringContextHolder.getBean(RoleMenuService.class);
    //private static MenuService menuService = SpringContextHolder.getBean(MenuService.class);

    /**
     * 验证权限方法
     * @Param user 用户
     * @Param tagCode 当前访问的权限标识
     * @return boolean
     */
    public static boolean validatePermission(User user, String tagCode) {

        //todo 考虑白名单
        //用户不存在
        if(StringUtils.isNotEmpty(user.getName())){
            //获取用户的权限
            Set<String> permissionList = getUserAuth(user.getName());
            //循环遍历匹配
            for (String permission : permissionList) {
                if (tagCode.equals(permission)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取用户权限
     * @Param userName 用户名
     * @return 用户权限集合
     */
    public static Set<String> getUserAuth(String userName) {
        //权限集合
        Set<String> permissionList = new HashSet<>();
        permissionList.add("test");
        //获取用户角色
        /*UserRole userRole = new UserRole();
        userRole.setUserId(userName);
        List<UserRole> userRoleList = userRoleService.findList(userRole);
        //获取角色菜单
        for (UserRole uR : userRoleList) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(uR.getRoleId());
            List<RoleMenu> roleMenuList = roleMenuService.findList(roleMenu);
            for (RoleMenu rm : roleMenuList) {
                Menu menu = menuService.get(new Menu(rm.getMenuId()));
                permissionList.add(menu.getPermission());
            }
        }*/
        //放进缓存 todo
        return permissionList;
    }
}
