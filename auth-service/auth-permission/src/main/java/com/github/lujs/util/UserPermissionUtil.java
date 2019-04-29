package com.github.lujs.util;

import com.github.lujs.user.api.model.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Describe: 用户权限验证工具
 * @Author: lujs
 * @Date: 2019/4/29 13:29
 * @Version: 1.0.0
 **/
public class UserPermissionUtil {

    /**
     * 验证权限方法
     * @return boolean
     */
    public static boolean validatePermission(User user, HttpServletRequest request){

        //获取访问url
        String url = request.getHeader("x-user-serviceName");
        //用户不存在
        if(StringUtils.isEmpty(user.getName())){
            return false;
        }else{
            //获取用户的权限 todo
            //循环遍历匹配
            System.out.println("默认允许通过！");
        }
        return true;
    }
}
