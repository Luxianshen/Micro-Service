package com.github.lujs.userapiimpl.service;

import com.github.lujs.user.api.model.User;

/**
 * @Description: 用户服务
 * @Author: lujs
 * @Data: 2019/5/1 11:08
 * @version: 1.0.0
 */

public interface UserService  {

    public User get(User user);

    public User getUserInfoByName(String userName);

}
