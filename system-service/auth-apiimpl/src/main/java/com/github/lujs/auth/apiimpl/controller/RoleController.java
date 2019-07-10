package com.github.lujs.auth.apiimpl.controller;

import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.auth.apiimpl.service.RoleService;
import com.github.lujs.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 角色控制层
 * @Author: lujs
 * @Data: 2019/5/18 10:51
 * @version: 1.0.0
 */

@RequestMapping("/v1/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /*@Autowired
    private RoleMenuService roleMenuService;*/


    /**
     * 根据id获取角色
     *
     * @param id id
     * @return Role
     * @author tangyi
     * @date 2018/9/14 18:20
     */
    @GetMapping("/{id}")
    public Role role(@PathVariable String id) {
        try {
            return roleService.get(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new Role();
    }

    /**
     * 角色分页查询
     *
     * @param pageNum  pageNum
     * @param pageSize pageSize
     * @param sort     sort
     * @param order    order
     * @param role     role
     * @return PageInfo
     * @author tangyi
     * @date 2018/10/24 22:13
     */
   /* @RequestMapping("roleList")
    @ApiOperation(value = "获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = "role", value = "角色信息", dataType = "Role")
    })
    public PageInfo<Role> userList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
                                   @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
                                   @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                   @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                   Role role) {
        // 查询所属部门
        PageInfo<Role> pageInfo = roleService.findPage(PageUtil.pageInfo(pageNum, pageSize, sort, order), role);
        Stream<Role> roleStream = pageInfo.getList().stream();
        if (Optional.ofNullable(roleStream).isPresent()) {
            roleStream.forEach(tempRole -> {
                RoleDept roleDept = new RoleDept();
                roleDept.setRoleId(tempRole.getId());
                // 查询角色部门关系
                roleDept = roleDeptService.get(roleDept);
                if (roleDept != null) {
                    // 查询部门信息
                    Dept dept = new Dept();
                    dept.setId(roleDept.getDeptId());
                    dept = deptService.get(dept);
                    // 设置角色所属部门ID和名称
                    if (dept != null) {
                        tempRole.setDeptId(roleDept.getDeptId());
                        tempRole.setDeptName(dept.getDeptName());
                    }
                }
            });
        }
        return pageInfo;
    }*/



    /**
     * 修改角色
     *
     * @param role role
     * @return ResponseBean
     * @author tangyi
     * @date 2018/9/14 18:22
     */
    @PutMapping
    public ResponseBean<Boolean> updateRole(@RequestBody Role role) {
        //role.setCommonValue(SecurityUtil.getCurrentUsername(), SysUtil.getSysCode());
        return new ResponseBean<>(roleService.update(role) > 0);
    }

    /**
     * 更新角色菜单
     *
     * @param role role
     * @return ResponseBean
     * @author tangyi
     * @date 2018/10/28 14:20
     */
    @PutMapping("roleMenuUpdate")
    public ResponseBean<Boolean> updateRoleMenu(@RequestBody Role role) {
        boolean success = false;
        if (StringUtils.isNotBlank(role.getId())) {
            role = roleService.get(role);
            // 保存角色菜单关系
            //if (role != null) todo
                //success = roleMenuService.saveRoleMenus(role.getId(), Stream.of(deptId.split(",")).collect(Collectors.toList())) > 0;
        }
        return new ResponseBean<>(success);
    }

    /**
     * 创建角色
     *
     * @param role role
     * @return ResponseBean
     * @author tangyi
     * @date 2018/9/14 18:23
     */
    @PostMapping
    public ResponseBean<Boolean> role(@RequestBody Role role) {
        //role.setCommonValue(SecurityUtil.getCurrentUsername(), SysUtil.getSysCode());
        return new ResponseBean<>(roleService.insert(role) > 0);
    }

    /**
     * 根据id删除角色
     *
     * @param id id
     * @return Role
     * @author tangyi
     * @date 2018/9/14 18:24
     */
    @DeleteMapping("/{id}")
    public ResponseBean<Boolean> deleteRole(@PathVariable String id) {
        Role role = new Role();
        role.setId(id);
        role.setNewRecord(false);
        //role.setCommonValue(SecurityUtil.getCurrentUsername(), SysUtil.getSysCode());
        return new ResponseBean<>(roleService.delete(role) > 0);
    }

    /**
     * 批量删除
     *
     * @param role role
     * @return ResponseBean
     * @author tangyi
     * @date 2018/12/4 10:00
     */
    @PostMapping("/deleteAll")
    public ResponseBean<Boolean> deleteAllRoles(@RequestBody Role role) {
        boolean success = false;
        try {
            if (StringUtils.isNotEmpty(role.getIdString()))
                success = roleService.deleteAll(role.getIdString().split(",")) > 0;
        } catch (Exception e) {
            logger.error("删除角色失败！", e);
        }
        return new ResponseBean<>(success);
    }
}
