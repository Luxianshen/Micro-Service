package com.github.lujs.userapiimpl.service.impl;

import com.github.lujs.service.CrudService;
import com.github.lujs.user.api.model.User;
import com.github.lujs.userapiimpl.mapper.UserMapper;
import com.github.lujs.userapiimpl.service.UserService;
import com.github.lujs.utils.ToolSecurityPbkdf2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @Description: 用户服务实现类
 * @Author lujs
 * @Date 2019/7/11 9:53
 */
@Service
@Slf4j
public class UserServiceImpl extends CrudService<UserMapper,User> implements UserService{

    /**
     * 获取用户信息方法
     * @param userName 用户名
     * @return 用户对象
     */
    @Override
    public User getUserInfoByName(String userName) {
        User user  = new User();
        user.setId("999");
        user.setUsername("lisi");
        user.setStatus("0");

        try {
            user.setPassword(ToolSecurityPbkdf2.getEncryptedPassword("123",new byte[1]));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        user.setSalt(new byte[1]);
        return user;
    }
}
