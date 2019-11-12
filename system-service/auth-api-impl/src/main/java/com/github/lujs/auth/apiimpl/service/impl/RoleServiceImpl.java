package com.github.lujs.auth.apiimpl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.auth.api.model.Role.Role;
import com.github.lujs.auth.apiimpl.mapper.RoleMapper;
import com.github.lujs.auth.api.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @Description: 角色服务实现类
 * @Author lujs
 * @Date 2019/7/11 11:36
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
