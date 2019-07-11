package com.github.lujs.util;


import com.github.lujs.user.api.model.UserInfo;

import java.util.List;
import java.util.Set;

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
     * @return boolean
     * @Param user 用户
     * @Param tagCode 当前访问的权限标识
     */
    public static boolean validatePermission(UserInfo userInfo, String tagCode) {

        //todo 考虑白名单
        List<String> permissionCodeList = userInfo.getPermissionList();
        if (permissionCodeList.contains(tagCode)) {
            return true;
        }
        return false;
    }
}
