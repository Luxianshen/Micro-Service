package com.github.lujs.util;

import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.api.model.RoleMenu.RoleMenu;
import com.github.lujs.auth.api.model.UserRole.UserRole;
import com.github.lujs.auth.apiimpl.service.MenuService;
import com.github.lujs.auth.apiimpl.service.RoleMenuService;
import com.github.lujs.auth.apiimpl.service.UserRoleService;
import com.github.lujs.user.api.model.User;
import com.github.lujs.userapiimpl.service.UserService;
import com.github.lujs.userapiimpl.utils.UserUtils;
import com.github.lujs.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Describe: 用户权限验证工具
 * @Author: lujs
 * @Date: 2019/4/29 13:29
 * @Version: 1.0.0
 **/
public class UserPermissionUtil {

    private static UserRoleService userRoleService = SpringContextHolder.getBean(UserRoleService.class);
    private static RoleMenuService roleMenuService = SpringContextHolder.getBean(RoleMenuService.class);
    private static MenuService menuService = SpringContextHolder.getBean(MenuService.class);

    /**
     * 验证权限方法
     * @return boolean
     */
    public static boolean validatePermission(User user, HttpServletRequest request){

        //获取访问url
        String url = request.getHeader("x-user-serviceName");
        //根据url 获取菜单标识
        Menu menu = menuService.get(new Menu("1"));
        //用户不存在
        if(StringUtils.isEmpty(user.getName())|| null ==menu){
            return false;
        }else{
            //当前访问权限标识
            String tagCode = menu.getPermission();
            //获取用户的权限
            Set<String> permissionList = getUserAuth(user.getName());
            //循环遍历匹配
            for (String permission : permissionList) {
                 if(tagCode.equals(permission)){
                     System.out.println("默认允许通过！");
                     return true;
                 }
            }
            return false;
        }
    }

    /**
     * 获取用户权限
     * @return 用户权限集合
     */
    public static Set<String> getUserAuth(String userName){
        //权限集合
        Set<String> permissionList = new HashSet<>();
        //获取用户角色
        UserRole userRole = new UserRole();
        userRole.setUserId(userName);
        List<UserRole> userRoleList = userRoleService.findList(userRole);
        //获取角色菜单
        for (UserRole uR:userRoleList) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(uR.getRoleId());
            List<RoleMenu> roleMenuList = roleMenuService.findList(roleMenu);
            for (RoleMenu rm: roleMenuList){
               Menu menu = menuService.get(new Menu(rm.getMenuId()));
                permissionList.add(menu.getPermission());
            }
        }
        //放进缓存 todo

        return permissionList;
    }
}
