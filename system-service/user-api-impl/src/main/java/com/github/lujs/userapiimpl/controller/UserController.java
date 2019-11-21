package com.github.lujs.userapiimpl.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.model.BaseRequest;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.model.request.PrimaryKeyRequest;
import com.github.lujs.user.api.model.User;
import com.github.lujs.user.api.model.UserUpdateRequest;
import com.github.lujs.user.api.service.UserService;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Describe: 用户控制层
 * @Author: lujs
 * @Date: 2019/4/29 15:05
 * @Version: 1.0.0
 **/

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/checkUserLoginInfo")
    @Permission(action = Action.Skip)
    public User checkUserLoginInfo(@RequestParam("agentId") String agentId,@RequestParam("agentAuth") String agentAuth) {
        return userService.checkUserLoginInfo(agentId,agentAuth);
    }




    @RequestMapping(value = "/info")
    @Permission(action = Action.Skip)
    public Object testUser() {
        User user = userService.getById(1L);
        return user;
    }

    /**
     * 用户分页
     * @return
     */
    @RequestMapping(value = "/page")
    @Permission(action = Action.Skip)
    public BaseResponse page() {
        IPage<User> page = new Page<>();
        return successResponse(userService.page(page));
    }

    /**
     * 获取用户信息
     */
    @PostMapping("/get")
    @Permission(action = Action.Skip)
    public BaseResponse get(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(userService.getById(request.getData()));
    }

    /**
     * 保存用户
     */
    @PostMapping("/save")
    @Permission(action = Action.Skip)
    public BaseResponse save(@RequestBody BaseRequest<User> request) {
        boolean flag = userService.save(request.getData());
        return successResponse(flag);
    }

    /**
     * 更新用户
     */
    @PostMapping("/update")
    @Permission(action = Action.Skip)
    public BaseResponse update(@Valid @RequestBody BaseRequest<User> request) {
        User user = new User();
        BeanUtils.copyProperties(request.getData(),user);
        boolean flag = userService.updateById(user);
        return successResponse(flag);
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    @Permission(action = Action.Skip)
    public BaseResponse delete(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(userService.removeById(request.getData().getId()));
    }

}

