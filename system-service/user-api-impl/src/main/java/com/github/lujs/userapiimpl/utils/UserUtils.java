package com.github.lujs.userapiimpl.utils;

import com.github.lujs.user.api.model.User;
import com.github.lujs.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 用户工具类
 * @Author: lujs
 * @Data: 2019/5/17 21:16
 * @version: 1.0.0
 */
public class UserUtils {

    private static UserService userService = SpringContextHolder.getBean(UserService.class);

    /**
     * 根据userName获取用户
     * @param userName
     * @return user
     */
    public static User getUser(String userName){
        if(StringUtils.isNotEmpty(userName)){
            User user = new User();
            user.setName(userName);
            return userService.get(new User(userName));
        }
        return null;
    }

}
