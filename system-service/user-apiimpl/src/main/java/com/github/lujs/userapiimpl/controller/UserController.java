package com.github.lujs.userapiimpl.controller;

import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.user.api.model.User;
import com.github.lujs.userapiimpl.service.UserService;
import com.github.lujs.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Describe: 用户控制层
 * @Author: lujs
 * @Date: 2019/4/29 15:05
 * @Version: 1.0.0
 **/

@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/test")
    @Permission("test")
    public String test() {
        User user = userService.getUserInfoByName("1");
        System.out.println("----------------success access provider service----------------");
        return "success access provider service!";
    }

    @GetMapping("/hi/test")
    @Permission("hi")
    public String hi() {
        User user = userService.getUserInfoByName("1");
        System.out.println("----------------success access provider service----------------");
        return "success access provider service!";
    }

    @GetMapping("/user/get/{id}")
    @Permission("test")
    public BaseResponse get(@PathVariable String id) {
        User user = new User(id);
        user = userService.get(user);
        return new BaseResponse(user);
    }

    @RequestMapping(value="/info",produces = "application/json; charset=utf-8")
    @Permission(action = Action.Skip)
    @ResponseBody
    public String testUser(){
        String a = "{\"msg\":\"success\",\"code\":200,\"status\":200,\"data\":{\"id\":\"596078038307966976\",\"identityType\":1,\"identifier\":\"admin\",\"name\":\"管理员\",\"phone\":\"15521089185\",\"avatarId\":\"596386542696665088\",\"avatarUrl\":\"http://118.25.138.130:8080/group1/M00/00/00/rBEADV0dwcCAdphxAAZ138_p__094.jpeg\",\"email\":\"1633736729@qq.com\",\"sex\":1,\"born\":1562256000000,\"deptName\":null,\"deptId\":\"571347099191480320\",\"remark\":null,\"status\":0,\"permissions\":[\"exam:course:add\",\"exam:course:edit\",\"exam:course:del\",\"exam:exam:add\",\"exam:exam:edit\",\"exam:exam:del\",\"exam:exam:subject\",\"exam:exam:subject:export\",\"exam:exam:subject:import\",\"exam:exam:subject:add\",\"exam:exam:subject:del\",\"exam:subject:bank:add\",\"exam:subject:bank:edit\",\"exam:subject:bank:del\",\"exam:subject:category:add\",\"exam:subject:category:edit\",\"exam:subject:category:del\",\"exam:subject:bank:import\",\"exam:subject:bank:export\",\"exam:examRecord:export\",\"monitor:log:del\",\"sys:user:add\",\"sys:user:del\",\"sys:user:edit\",\"sys:user:export\",\"sys:user:import\",\"sys:dept:add\",\"sys:dept:edit\",\"sys:dept:del\",\"sys:role:add\",\"sys:role:edit\",\"sys:role:del\",\"sys:role:auth\",\"sys:menu:add\",\"sys:menu:edit\",\"sys:menu:del\",\"sys:menu:import\",\"sys:menu:export\",\"sys:client:add\",\"sys:client:edit\",\"sys:client:del\",\"sys:route:add\",\"sys:route:edit\",\"sys:route:del\",\"sys:route:refresh\",\"exam:exam:subject:edit\",\"tenant:tenant:add\",\"tenant:tenant:edit\",\"tenant:tenant:del\"],\"roles\":[\"role_admin\"],\"applicationCode\":\"EXAM\",\"tenantCode\":\"gitee\"}}";
        return a;
    }
}

