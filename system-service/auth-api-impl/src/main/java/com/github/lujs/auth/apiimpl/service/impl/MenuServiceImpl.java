package com.github.lujs.auth.apiimpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.api.model.Role.VOrgTree;
import com.github.lujs.auth.api.service.MenuService;
import com.github.lujs.auth.apiimpl.mapper.MenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 菜单服务实现类
 * @Author lujs
 * @Date 2019/7/11 11:33
 */
@AllArgsConstructor
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<VOrgTree> trees(Long sysId) {
        List<VOrgTree> trees = new ArrayList<>();
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("sys_id", sysId);
        wrapper.isNull("pid");
        Menu permission = getOne(wrapper);
        if (null != permission) {
            VOrgTree tree = new VOrgTree();
            tree.setId(permission.getId());
            tree.setLabel(permission.getLabel());
            findChildrenPermission(tree);
            trees.add(tree);
        }
        return trees;
    }

    private void findChildrenPermission(VOrgTree parent) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", parent.getId());
        List<Menu> children = list(wrapper);
        List<VOrgTree> childrenTree = new ArrayList<>();
        for (Menu p : children) {
            VOrgTree cTree = new VOrgTree();
            cTree.setId(p.getId());
            cTree.setLabel(p.getLabel());
            findChildrenPermission(cTree);
            childrenTree.add(cTree);
        }
        parent.setChildren(childrenTree.size() > 0 ? childrenTree : null);
    }
}
