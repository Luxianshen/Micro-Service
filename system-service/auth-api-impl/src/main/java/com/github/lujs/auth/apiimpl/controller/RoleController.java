package com.github.lujs.auth.apiimpl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.auth.api.service.RoleService;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 角色控制层
 * @Author: lujs
 * @Data: 2019/5/18 10:51
 * @version: 1.0.0
 */
@AllArgsConstructor
@RequestMapping("/v1/role")
@RestController
public class RoleController extends BaseController {

    public final RoleService targetService;

    /**
     * page分页
     */
    @RequestMapping("/page")
    public BaseResponse page(){

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        Page<Role> rolePage = new Page<>();
        return new BaseResponse(targetService.page(rolePage,queryWrapper));
    }


}
