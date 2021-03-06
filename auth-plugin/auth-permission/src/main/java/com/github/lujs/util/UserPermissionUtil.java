package com.github.lujs.util;


import com.github.lujs.user.api.model.UserInfo;

import java.util.List;

/**
 * @Describe: 用户权限验证工具
 * @Author: lujs
 * @Date: 2019/4/29 13:29
 * @Version: 1.0.0
 **/
public class UserPermissionUtil {

    /**
     * 验证权限方法
     *
     * @param userInfo 用户
     * @pram tagCode 当前访问的权限标识
     * @return boolean
     */
    public static boolean validatePermission(UserInfo userInfo, String tagCode) {

        //todo 考虑白名单
        List<String> permissionCodeList = userInfo.getPermissionList();
        return permissionCodeList.contains(tagCode);
    }
}
