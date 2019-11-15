package com.github.lujs.auth.apiimpl.controller;

import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.auth.api.service.RoleService;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 角色控制层
 * @Author: lujs
 * @Data: 2019/5/18 10:51
 * @version: 1.0.0
 */
@AllArgsConstructor
@RequestMapping("/v1/auth/role")
@RestController
public class RoleController extends BaseController {

    public final RoleService targetService;

    /**
     * page分页
     */
    @RequestMapping("/page")
    public BaseResponse page(){

        /*QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        Page<Role> rolePage = new Page<>();
        targetService.page(rolePage,queryWrapper)*/
        return new BaseResponse();
    }

    /**
     * 获取用户角色
     */
    @GetMapping("getUserRoleList")
    @Permission(action = Action.Skip)
    public List<String> getUserRoleList(@RequestParam("userId") String userId){
        List<String> roles = new ArrayList<>();
        roles.add("test");
        return roles;
    }


}
