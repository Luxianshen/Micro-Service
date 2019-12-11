package com.github.lujs.auth.apiimpl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.auth.api.model.Role.RoleDto;
import com.github.lujs.auth.api.model.Role.RolePermissionQuery;
import com.github.lujs.auth.api.model.Role.VOrgTree;
import com.github.lujs.auth.api.model.RoleMenu.RoleMenu;
import com.github.lujs.auth.api.model.UserRole.UserRole;
import com.github.lujs.auth.api.service.MenuService;
import com.github.lujs.auth.api.service.RoleMenuService;
import com.github.lujs.auth.api.service.RoleService;
import com.github.lujs.auth.api.service.UserRoleService;
import com.github.lujs.model.BaseRequest;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.model.request.PageQuery;
import com.github.lujs.model.request.PrimaryKeyRequest;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 菜单控制层
 * @Author: lujs
 * @Data: 2019/5/18 10:25
 * @version: 1.0.0
 */
@AllArgsConstructor
@RestController
@RequestMapping("/v1/role")
public class RoleController extends BaseController {

    public final RoleService roleService;

    public final MenuService menuService;

    public final UserRoleService userRoleService;

    public final RoleMenuService roleMenuService;

    public final RedisTemplate redisTemplate;


//    role分界线

    /**
     * 获取用户角色
     */
    @PostMapping("/getUserRoleList")
    @Permission(action = Action.Skip)
    public List<String> getUserRoleList(@RequestParam("userId") Long userId) {
        return roleService.getUserRoleList(userId);
    }

    /**
     * 获取角色用户
     */
    @PostMapping("/getRoleUserList")
    @Permission(action = Action.Skip)
    public List<String> getRoleUserList(@RequestParam("roleId") Long roleId) {
        return roleService.getRoleUserList(roleId);
    }


    /**
     * 分页查询角色信息
     *
     * @param request
     * @return
     */
    @PostMapping("/page")
    @Permission(action = Action.Skip)
    public BaseResponse rolePage() {
        IPage<Role> page = new Page<>();
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleService.page(page, roleQueryWrapper);
        return successResponse(page);
    }

    /**
     * 查询角色信息
     *
     * @param request
     * @return
     */
    @PostMapping("/get")
    @Permission(action = Action.Skip)
    public BaseResponse roleGet(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(roleService.getById(request.getData()));
    }

    /**
     * 新增角色
     */
    @PostMapping("/save")
    @Permission(action = Action.Skip)
    public BaseResponse roleSave(@Valid @RequestBody BaseRequest<Role> request) {
        Role role = request.getData();
        role.init();
        return successResponse(roleService.save(request.getData()));
    }

    /**
     * 更新角色
     */
    @PostMapping("/update")
    @Permission(action = Action.Skip)
    public BaseResponse roleUpdate(@Valid @RequestBody BaseRequest<Role> request) {
        return successResponse(roleService.updateById(request.getData()));
    }

    /**
     * 删除角色
     *
     * @param request
     * @return
     */
    @PostMapping("/delete")
    @Permission(action = Action.Skip)
    public BaseResponse roleDelete(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(roleService.removeById(request.getData()));
    }

    /**
     * 角色授权用户分页查询
     *
     * @param request
     * @return
     */
    @PostMapping("/authUserPage")
    @Permission(action = Action.Skip)
    public BaseResponse authUserPage(@RequestBody BaseRequest<PageQuery<RoleDto, RoleDto>> request) {

        IPage<RoleDto> page = request.getData();
        RoleDto params = request.getData().getParams();
        roleService.authUserPage(page, params);
        return successResponse(page);
    }

    /**
     * 菜单权限树
     *
     * @param request
     * @return
     */
    @PostMapping("/findAuthPermissionTree")
    @Permission(action = Action.Skip)
    public BaseResponse findAuthPermissionTree(@Valid @RequestBody BaseRequest<RolePermissionQuery> request) {
        List<VOrgTree> list = roleService.findRolePermissionTree(request.getData());
        return successResponse(list);
    }

    /**
     * 角色添加用户
     *
     * @param request
     * @return
     */
    @PostMapping("/addUser")
    @Permission(action = Action.Skip)
    public BaseResponse addUser(@Valid @RequestBody BaseRequest<UserRole> request) {
        return baseResponse(userRoleService.save(request.getData()));
    }

    /**
     * 角色添加用户
     *
     * @param request
     * @return
     */
    @PostMapping("/removeUser")
    @Permission(action = Action.Skip)
    public BaseResponse removeUser(@Valid @RequestBody BaseRequest<UserRole> request) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", request.getData().getUserId());
        queryWrapper.eq("role_id", request.getData().getRoleId());
        return baseResponse(userRoleService.remove(queryWrapper));
    }

    /**
     * 权限授权
     *
     * @param request
     * @return
     */
    @PostMapping("/grant")
    @Permission(action = Action.Skip)
    public BaseResponse grant(@Valid @RequestBody BaseRequest<RoleMenu> request) {
        return baseResponse(roleMenuService.save(request.getData()));
    }

    /**
     * 取消授权
     *
     * @param request
     * @return
     */
    @PostMapping("/revoke")
    @Permission(action = Action.Skip)
    public BaseResponse revoke(@Valid @RequestBody BaseRequest<RoleMenu> request) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", request.getData().getRoleId());
        queryWrapper.eq("menu_id", request.getData().getMenuId());
        return baseResponse(roleMenuService.remove(queryWrapper));
    }


}
