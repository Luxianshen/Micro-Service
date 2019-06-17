package com.github.lujs.userapiimpl.controller;

import com.github.lujs.annotation.Permission;
import com.github.lujs.model.ResponseBean;
import com.github.lujs.user.api.model.User;
import com.github.lujs.userapiimpl.service.UserService;
import com.github.lujs.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Describe: 用户控制层
 * @Author: lujs
 * @Date: 2019/4/29 15:05
 * @Version: 1.0.0
 **/

@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/test")
    @Permission("test")
    public String test() {
        User user = userService.getTest();
        System.out.println("----------------success access provider service----------------");
        return "success access provider service!";
    }

    @GetMapping("/user/get/{id}")
    @Permission("test")
    public ResponseBean<User> get(@PathVariable String id) {
        User user = new User(id);
        user = userService.get(user);
        return new ResponseBean<>(user);
    }

}

