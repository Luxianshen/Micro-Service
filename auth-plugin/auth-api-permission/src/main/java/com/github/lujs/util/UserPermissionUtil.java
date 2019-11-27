package com.github.lujs.util;


import com.github.lujs.user.api.model.UserInfo;
import com.github.lujs.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;

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
     * @return boolean
     * @Param user 用户
     * @Param tagCode 当前访问的权限标识
     */
    public static boolean validatePermission(UserInfo userInfo, String tagCode) {

        //获取接口权限
        String tagPermission = (String) RedisUtil.hashGet("apiMap",tagCode);
        if(StringUtils.isNotEmpty(tagPermission)){
            List<String> apiCodeList = userInfo.getApiList();
            if (apiCodeList.contains(tagPermission)) {
                return true;
            }
        }
        return false;
    }
}
