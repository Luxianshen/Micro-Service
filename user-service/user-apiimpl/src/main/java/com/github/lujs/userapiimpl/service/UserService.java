package com.github.lujs.userapiimpl.service;

import com.github.lujs.service.CrudService;
import com.github.lujs.user.api.model.User;
import com.github.lujs.userapiimpl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 用户服务
 * @Author: lujs
 * @Data: 2019/5/1 11:08
 * @version: 1.0.0
 */

@Service
public class UserService extends CrudService<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;

    @CacheEvict(key = "#user.name",value = "user")
    public User getTest(){
        User user = new User("1","lisi");
        return user;
    }

//    @Override
//    @CacheEvict(key = "#user.name",value = "user")
//    public User get(User user){
//
//        List<User> userList = super.findList(user);
//        return userList.get(0);
//    }

}
