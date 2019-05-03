package com.github.lujs.userapiimpl.controller;

import com.github.lujs.user.api.model.User;
import com.github.lujs.userapiimpl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describe: 用户控制层
 * @Author: lujs
 * @Date: 2019/4/29 15:05
 * @Version: 1.0.0
 **/

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/test")
    public String test() {
        User user = userService.get();
        System.out.println("----------------success access provider service----------------");
        return "success access provider service!";
    }
}

