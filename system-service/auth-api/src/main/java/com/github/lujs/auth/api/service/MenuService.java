package com.github.lujs.auth.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.api.model.Role.VOrgTree;

import java.util.List;

/**
 * @Description: 菜单服务
 * @Author: lujs
 * @Data: 2019/5/18 10:30
 * @version: 1.0.0
 */
public interface MenuService extends IService<Menu> {

    List<VOrgTree> trees(Long sysId);
}
