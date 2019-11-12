package com.github.lujs.auth.apiimpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.auth.api.model.UserRole.UserRole;
import com.github.lujs.auth.apiimpl.mapper.UserRoleMapper;
import com.github.lujs.auth.api.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 用户角色权限类
 * @Author lujs
 * @Date 2019/7/11 11:37
 */
@AllArgsConstructor
@Service
public class UserRoleServiceImpl  extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private UserRoleService userRoleService;


    /**
     * 返回用户的角色
     * @param userId 用户ID
     * @return 用户角色
     */
    @Override
    public List<String> getUserRoleList(String userId) {
        List<String> roleList = new ArrayList<>();
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<UserRole> userRoleList = userRoleService.list(queryWrapper);
        if(ObjectUtils.isNotEmpty(userRoleList)){
            userRoleList.forEach(x->roleList.add(x.getRoleId()));
        }
        return roleList;
    }
}
