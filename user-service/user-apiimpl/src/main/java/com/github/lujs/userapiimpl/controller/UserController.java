package com.github.lujs.userapiimpl.controller;

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

    @GetMapping("/user/test")
    public String test() {
        System.out.println("----------------success access provider service----------------");
        return "success access provider service!";
    }
}

