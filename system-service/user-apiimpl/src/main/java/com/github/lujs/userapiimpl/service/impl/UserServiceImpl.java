package com.github.lujs.userapiimpl.service.impl;

import com.github.lujs.service.CrudService;
import com.github.lujs.user.api.model.User;
import com.github.lujs.userapiimpl.mapper.UserMapper;
import com.github.lujs.userapiimpl.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户服务实现类
 * @Author lujs
 * @Date 2019/7/11 9:53
 */
@Service
public class UserServiceImpl extends CrudService<UserMapper,User> implements UserService{

    /**
     * 获取用户信息方法
     * @param userName
     * @return
     */
    @Override
    public User getUserInfoByName(String userName) {
        return new User();
    }
}
