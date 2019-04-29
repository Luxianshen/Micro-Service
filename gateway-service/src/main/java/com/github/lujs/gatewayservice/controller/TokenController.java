package com.github.lujs.gatewayservice.controller;

import com.github.lujs.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describe: jwt token生成控制层
 * @Author: lujs
 * @Date: 2019/4/29 9:43
 * @Version: 1.0.0
 **/

@RestController
@RequestMapping("/token")
public class TokenController {

    @GetMapping("/getToken/{userName}")
    public String get(@PathVariable("userName") String userName){

        return JwtUtil.generateToken(userName);
    }
}
