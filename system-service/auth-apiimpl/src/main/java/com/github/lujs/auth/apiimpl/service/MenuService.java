package com.github.lujs.auth.apiimpl.service;

import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.apiimpl.mapper.MenuMapper;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 菜单服务
 * @Author: lujs
 * @Data: 2019/5/18 10:30
 * @version: 1.0.0
 */
@Service
public class MenuService extends CrudService<MenuMapper, Menu> {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据角色查找菜单
     *
     * @param role 角色
     * @return List
     * @author tangyi
     * @date 2018/8/27 16:00
     */
    @Cacheable(value = "menu", key = "#role")
    public List<Menu> findMenuByRole(String role) {
        return menuMapper.findByRole(role);
    }

    /**
     * 查询全部菜单
     *
     * @param menu menu
     * @return List
     * @author tangyi
     * @date 2019/04/10 17:58
     */
    @Cacheable(value = "menu", key = "#menu.applicationCode")
    @Override
    public List<Menu> findAllList(Menu menu) {
        return menuMapper.findAllList(menu);
    }

    /**
     * 新增菜单
     *
     * @param menu menu
     * @return int
     * @author tangyi
     * @date 2018/10/28 15:56
     */
    @Transactional
    @Override
    public int insert(Menu menu) {
        return super.insert(menu);
    }

    /**
     * 更新菜单
     *
     * @param menu menu
     * @return int
     * @author tangyi
     * @date 2018/10/30 20:19
     */
    @Transactional
    @Override
    @CacheEvict(value = {"menu", "user"}, allEntries = true)
    public int update(Menu menu) {
        return super.update(menu);
    }

    /**
     * 删除菜单
     *
     * @param menu menu
     * @return int
     * @author tangyi
     * @date 2018/8/27 16:22
     */
    @Override
    @Transactional
    @CacheEvict(value = "menu", allEntries = true)
    public int delete(Menu menu) {
        // 删除当前菜单
        super.delete(menu);
        // 删除父节点为当前节点的菜单
        Menu parentMenu = new Menu();
        parentMenu.setParentId(menu.getId());
        parentMenu.setNewRecord(false);
        //parentMenu.setCommonValue(SecurityUtil.getCurrentUsername(), SysUtil.getSysCode());
        parentMenu.setDelFlag(CommonConstant.DEL_FLAG_DEL);
        return super.update(parentMenu);
    }
}
