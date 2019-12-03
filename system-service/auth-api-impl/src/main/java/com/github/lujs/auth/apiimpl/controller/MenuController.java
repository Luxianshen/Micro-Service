package com.github.lujs.auth.apiimpl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.api.model.Role.*;
import com.github.lujs.auth.api.model.RoleMenu.RoleMenu;
import com.github.lujs.auth.api.model.UserRole.UserRole;
import com.github.lujs.auth.api.service.MenuService;
import com.github.lujs.auth.api.service.RoleMenuService;
import com.github.lujs.auth.api.service.RoleService;
import com.github.lujs.auth.api.service.UserRoleService;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.model.BaseRequest;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.model.request.PageQuery;
import com.github.lujs.model.request.PrimaryKeyRequest;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/v1/menu")
public class MenuController extends BaseController {

    public final RoleService roleService;

    public final MenuService menuService;

    public final UserRoleService userRoleService;

    public final RoleMenuService roleMenuService;

    public final RedisTemplate redisTemplate;

    /**
     * 获取用户权限
     */
    @PostMapping("/getRolePermissionList")
    @Permission(action = Action.Skip)
    public List<String> getRolePermissionList(@RequestBody RoleQuery roleQuery) {
        if (roleQuery.getRoles() == null || roleQuery.getRoles().size() < 1) {
            return new ArrayList<>();
        }
        return roleService.getUserPermissionList(roleQuery);
    }


    @Permission(action = Action.Skip)
    @RequestMapping(value = "/userMenu")
    public BaseResponse userMenu(HttpServletRequest request) {
        String agentId = request.getHeader("x-user-name");
        if (StringUtils.isNotEmpty(agentId)) {
            return successResponse(redisTemplate.opsForValue().get(agentId + "Menu"));
        }
        return failedResponse(GlobalStatusCode.FAILED);
    }

    /**
     * 菜单查询
     */
    @PostMapping("/page")
    @Permission(action = Action.Skip)
    public BaseResponse menuPage() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        Page<Menu> page = new Page<>();
        return successResponse(menuService.page(page, queryWrapper));
    }

    /**
     * 获取菜单
     *
     * @return
     */
    @PostMapping("/get")
    @Permission(action = Action.Skip)
    public BaseResponse menuGet(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(menuService.getById(request.getData()));
    }

    /**
     * 保存菜单
     */
    @PostMapping("/save")
    @Permission(action = Action.Skip)
    public BaseResponse menuSave(@Valid @RequestBody BaseRequest<Menu> request) {
        Menu menu = request.getData();
        menu.setLabel(menu.getName());
        menu.init();
        return successResponse(menuService.save(menu));
    }

    /**
     * 更新菜单
     */
    @PostMapping("/update")
    @Permission(action = Action.Skip)
    public BaseResponse menuUpdate(@Valid @RequestBody BaseRequest<Menu> request) {
        return successResponse(menuService.updateById(request.getData()));
    }

    /**
     * 删除菜单
     */
    @PostMapping("/delete")
    @Permission(action = Action.Skip)
    public BaseResponse menuDelete(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(menuService.removeById(request.getData()));
    }

}
