package com.github.lujs.auth.apiimpl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.auth.api.model.Role.RoleDto;
import com.github.lujs.auth.api.model.Role.VOrgTree;
import com.github.lujs.auth.api.service.MenuService;
import com.github.lujs.auth.api.service.RoleService;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 菜单控制层
 * @Author: lujs
 * @Data: 2019/5/18 10:25
 * @version: 1.0.0
 */
@AllArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController extends BaseController {

    public final RoleService roleService;

    public final MenuService menuService;

    public final RedisTemplate redisTemplate;

    /**
     * 获取用户权限
     */
    @PostMapping("/menu/getRolePermissionList")
    @Permission(action = Action.Skip)
    public List<String> getRolePermissionList(@RequestBody List<String> roles) {

        if (roles == null || roles.size() < 1) {
            return new ArrayList<>();
        }
        return roleService.getUserPermissionList(roles);
    }


    @Permission(action = Action.Skip)
    @RequestMapping(value = "/menu/userMenu", produces = "application/json; charset=utf-8")
    public Object testMenu() {
        return redisTemplate.opsForValue().get("test");
    }

    /**
     * 获取用户角色
     */
    @PostMapping("/role/getUserRoleList")
    @Permission(action = Action.Skip)
    public List<String> getUserRoleList(@RequestParam("userId") Long userId) {
        return roleService.getUserRoleList(userId);
    }

    /**
     * 菜单查询
     */
    @PostMapping("/menu/page")
    @Permission(action = Action.Skip)
    public BaseResponse menuPage() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        Page<Menu> page = new Page<>();
        return successResponse(menuService.page(page,queryWrapper));
    }

    /**
     * 分页查询角色信息
     * @param request
     * @return
     */
    @PostMapping("/role/page")
    @Permission(action = Action.Skip)
    public BaseResponse rolePage() {
        IPage<Role> page = new Page<>();
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleService.page(page, roleQueryWrapper);
        return successResponse(page);
    }

    /**
     * 分页查询角色信息
     * @param request
     * @return
     */
    @PostMapping("/role/get")
    @Permission(action = Action.Skip)
    public BaseResponse get() {
        return successResponse(roleService.getById(1L));
    }

    /**
     *
     */
    /**
     * 角色授权用户分页查询
     * @param request
     * @return
     */
    @PostMapping("/role/authUserPage")
    @Permission(action = Action.Skip)
    public BaseResponse authUserPage() {
        IPage<RoleDto> page  = new Page<>();
        roleService.authUserPage(page);
        return successResponse(page);
    }

    /**
     * 权限树
     * @param request
     * @return
     */
    @PostMapping("/role/findAuthPermissionTree")
    @Permission(action = Action.Skip)
    public BaseResponse findAuthPermissionTree() {
        List<VOrgTree> list = roleService.findRolePermissionTree();
        return successResponse(list);
    }

}
