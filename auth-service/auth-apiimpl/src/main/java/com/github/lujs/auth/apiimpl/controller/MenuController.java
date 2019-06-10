package com.github.lujs.auth.apiimpl.controller;

import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.apiimpl.service.MenuService;
import com.github.lujs.model.ResponseBean;
import com.github.lujs.web.BaseController;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 菜单控制层
 * @Author: lujs
 * @Data: 2019/5/18 10:25
 * @version: 1.0.0
 */

@RequestMapping("/v1/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * 新增菜单
     *
     * @param menu menu
     * @return ResponseBean
     * @author tangyi
     * @date 2018/8/27 16:12
     */
    @PostMapping
    public ResponseBean<Boolean> addMenu(@RequestBody Menu menu) {
        //menu.setCommonValue(SecurityUtil.getCurrentUsername(), SysUtil.getSysCode());
        return new ResponseBean<>(menuService.insert(menu) > 0);
    }

    /**
     * 更新菜单
     *
     * @param menu menu
     * @return ResponseBean
     * @author tangyi
     * @date 2018/10/24 16:34
     */
    @PutMapping
    public ResponseBean<Boolean> updateMenu(@RequestBody Menu menu) {
        //menu.setCommonValue(SecurityUtil.getCurrentUsername(), SysUtil.getSysCode());
        return new ResponseBean<>(menuService.update(menu) > 0);
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return ResponseBean
     * @author tangyi
     * @date 2018/8/27 16:19
     */
    @DeleteMapping("/{id}")
    public ResponseBean<Boolean> deleteMenu(@PathVariable String id) {
        Menu menu = new Menu();
        menu.setId(id);
        return new ResponseBean<>(menuService.delete(menu) > 0);
    }

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return Menu
     * @author tangyi
     * @date 2018/8/27 16:11
     */
    @GetMapping("/{id}")
    public Menu menu(@PathVariable String id) {
        Menu menu = new Menu();
        menu.setId(id);
        return menuService.get(menu);
    }

    /**
     * 获取菜单分页列表
     *
     * @param pageNum  pageNum
     * @param pageSize pageSize
     * @param sort     sort
     * @param order    order
     * @param menu     menu
     * @return PageInfo
     * @author tangyi
     * @date 2018/8/26 23:17
     */
    /*@RequestMapping("/menuList")
    public PageInfo<Menu> menuList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
                                   @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
                                   @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                   @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                   Menu menu) {
        return menuService.findPage(PageUtil.pageInfo(pageNum, pageSize, sort, order), menu);
    }*/

    /**
     * 根据角色查找菜单
     *
     * @param role 角色
     * @return List
     * @author tangyi
     * @date 2018/8/27 15:58
     */
    @GetMapping("findMenuByRole/{role}")
    public List<Menu> findMenuByRole(@PathVariable String role) {
        return menuService.findMenuByRole(role);
    }

    /**
     * 查询所有菜单
     *
     * @return List
     * @author tangyi
     * @date 2019/04/26 11:50
     */
    @GetMapping("findAllMenu")
    public List<Menu> findAllMenu() {
        Menu menu = new Menu();
        //menu.setApplicationCode(SysUtil.getSysCode()); todo
        return menuService.findAllList(menu);
    }

    /**
     * 根据角色查找菜单
     *
     * @param roleCode 角色code
     * @return 属性集合
     */
    @GetMapping("/roleTree/{roleCode}")
    public List<String> roleTree(@PathVariable String roleCode) {
        // 根据角色查找菜单
        Stream<Menu> menuStream = menuService.findMenuByRole(roleCode).stream();
        // 获取菜单ID
        if (Optional.ofNullable(menuStream).isPresent())
            return menuStream.map(Menu::getId).collect(Collectors.toList());
        return new ArrayList<>();
    }

}
