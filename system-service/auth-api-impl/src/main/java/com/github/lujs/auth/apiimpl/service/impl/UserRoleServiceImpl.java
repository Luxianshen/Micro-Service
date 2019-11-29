package com.github.lujs.auth.apiimpl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.auth.api.model.UserRole.UserRole;
import com.github.lujs.auth.api.service.UserRoleService;
import com.github.lujs.auth.apiimpl.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户角色权限类
 * @Author lujs
 * @Date 2019/7/11 11:37
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {


    /**
     * 返回用户的角色
     *
     * @param userId 用户ID
     * @return 用户角色
     */
    @Override
    public List<String> getUserRoleList(Long userId) {

        List<String> roleList = baseMapper.getUserRoleList(userId);
        if (roleList == null || roleList.size() < 1) {
           return new ArrayList<>();
        }
        return roleList;
    }

    /**
     *
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<String> getRoleUserList(Long roleId) {
        List<String> userList = baseMapper.getRoleUserList(roleId);
        if (userList == null || userList.size() < 1) {
            return new ArrayList<>();
        }
        return userList;
    }
}
