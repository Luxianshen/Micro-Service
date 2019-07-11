package com.github.lujs.auth.apiimpl.service.impl;

import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.apiimpl.mapper.MenuMapper;
import com.github.lujs.auth.apiimpl.service.MenuService;
import com.github.lujs.service.CrudService;
import org.springframework.stereotype.Service;

/**
 * @Description: 菜单服务实现类
 * @Author lujs
 * @Date 2019/7/11 11:33
 */
@Service
public class MenuServiceImpl extends  CrudService<MenuMapper, Menu>  implements MenuService {

}
