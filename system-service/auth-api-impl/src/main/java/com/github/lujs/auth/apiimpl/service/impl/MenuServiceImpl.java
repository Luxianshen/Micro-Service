package com.github.lujs.auth.apiimpl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.auth.api.service.MenuService;
import com.github.lujs.auth.apiimpl.mapper.MenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 菜单服务实现类
 * @Author lujs
 * @Date 2019/7/11 11:33
 */
@AllArgsConstructor
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
