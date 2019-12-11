package com.github.lujs.user.apiimpl.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.model.BaseRequest;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.model.request.PrimaryKeyRequest;
import com.github.lujs.user.api.model.User;
import com.github.lujs.user.api.service.UserService;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public User checkUserLoginInfo(@RequestParam("agentId") String agentId, @RequestParam("agentAuth") String agentAuth) {
        return userService.checkUserLoginInfo(agentId, agentAuth);
    }


    @GetMapping(value = "/info")
    @Permission(action = Action.Skip)
    public BaseResponse info(HttpServletRequest request) {
        String agentId = request.getHeader("x-user-name");
        if (StringUtils.isNotEmpty(agentId)) {
            return successResponse(userService.getUserByAgentId(agentId));
        } else {
            return failedResponse("用户不存在！");
        }
    }

    /**
     * 用户分页
     *
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
    @PostMapping("/register")
    @Permission(action = Action.Skip)
    public BaseResponse register(@Valid @RequestBody BaseRequest<User> request) {
        if (null == userService.getUserByAgentId(request.getData().getAgentId()) && StringUtils.isNotEmpty(request.getData().getTmpAuth())) {
            return successResponse(userService.register(request.getData()));
        } else {
            return failedResponse("用户已存在！");
        }
    }

    /**
     * 更新用户
     */
    @PostMapping("/update")
    @Permission(action = Action.Skip)
    public BaseResponse update(@Valid @RequestBody BaseRequest<User> request) {
        User user = new User();
        BeanUtils.copyProperties(request.getData(), user);
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

    /**
     * 根据用户id,获取用户agentId
     */
    @PostMapping("/getUserAgentIds")
    @Permission(action = Action.Skip)
    public List<String> getUserAgentIds(@RequestBody List<String> userIds) {
        if (ObjectUtil.isEmpty(userIds)) {
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", userIds);
        return userService.list(queryWrapper).stream().map(User::getAgentId).collect(Collectors.toList());
    }

}

