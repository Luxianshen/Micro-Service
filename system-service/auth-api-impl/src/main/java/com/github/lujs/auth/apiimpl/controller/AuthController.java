package com.github.lujs.auth.apiimpl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.auth.api.model.Role.RoleDto;
import com.github.lujs.auth.api.model.Role.RoleQuery;
import com.github.lujs.auth.api.model.Role.VOrgTree;
import com.github.lujs.auth.api.service.MenuService;
import com.github.lujs.auth.api.service.RoleService;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.model.BaseRequest;
import com.github.lujs.model.BaseResponse;
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
    public List<String> getRolePermissionList(@RequestBody RoleQuery roleQuery) {
        if (roleQuery.getRoles() == null || roleQuery.getRoles().size() < 1) {
            return new ArrayList<>();
        }
        return roleService.getUserPermissionList(roleQuery);
    }


    @Permission(action = Action.Skip)
    @RequestMapping(value = "/menu/userMenu")
    public BaseResponse userMenu(HttpServletRequest request) {
        String agentId = request.getHeader("x-user-name");
        if(StringUtils.isNotEmpty(agentId)){
            return successResponse(redisTemplate.opsForValue().get(agentId+"Menu"));
        }
        return failedResponse(GlobalStatusCode.FAILED);
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
     * 获取菜单
     * @return
     */
    @PostMapping("/menu/get")
    @Permission(action = Action.Skip)
    public BaseResponse menuGet(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(menuService.getById(request.getData()));
    }

    /**
     * 保存菜单
     */
    @PostMapping("/menu/save")
    @Permission(action = Action.Skip)
    public BaseResponse menuSave(@Valid @RequestBody BaseRequest<Menu> request) {
        Menu menu = request.getData();
        menu.setLabel(menu.getName());
        menu.init();
        menuService.save(request.getData());
        return successResponse(true);
    }

    /**
     * 更新菜单
     */
    @PostMapping("/menu/update")
    @Permission(action = Action.Skip)
    public BaseResponse menuUpdate(@Valid @RequestBody BaseRequest<Menu> request) {
        return successResponse(menuService.updateById(request.getData()));
    }

    /**
     * 删除菜单
     */
    @PostMapping("/menu/delete")
    @Permission(action = Action.Skip)
    public BaseResponse menuDelete(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(menuService.removeById(request.getData()));
    }


//    role分界线
    /**
     * 获取用户角色
     */
    @PostMapping("/role/getUserRoleList")
    @Permission(action = Action.Skip)
    public List<String> getUserRoleList(@RequestParam("userId") Long userId) {
        return roleService.getUserRoleList(userId);
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
     * 查询角色信息
     * @param request
     * @return
     */
    @PostMapping("/role/get")
    @Permission(action = Action.Skip)
    public BaseResponse roleGet(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(roleService.getById(request.getData()));
    }

    /**
     * 新增角色
     */
    @PostMapping("/role/save")
    @Permission(action = Action.Skip)
    public BaseResponse roleSave(@Valid @RequestBody BaseRequest<Role> request) {
        Role role = request.getData();
        role.init();
        return successResponse(roleService.save(request.getData()));
    }

    /**
     * 更新角色
     */
    @PostMapping("/role/update")
    @Permission(action = Action.Skip)
    public BaseResponse roleUpdate(@Valid @RequestBody BaseRequest<Role> request) {
        return successResponse(roleService.updateById(request.getData()));
    }

    /**
     * 删除角色
     * @param request
     * @return
     */
    @PostMapping("/role/delete")
    @Permission(action = Action.Skip)
    public BaseResponse roleDelete(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(roleService.removeById(request.getData()));
    }

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
