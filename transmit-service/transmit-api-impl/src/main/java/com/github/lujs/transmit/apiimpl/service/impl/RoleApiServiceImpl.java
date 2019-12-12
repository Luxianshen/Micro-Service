package com.github.lujs.transmit.apiimpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.auth.api.model.Role.RoleQuery;
import com.github.lujs.transmit.api.model.ApiEntity;
import com.github.lujs.transmit.api.model.RoleApiEntity;
import com.github.lujs.transmit.api.service.RoleApiService;
import com.github.lujs.transmit.api.service.TransmitService;
import com.github.lujs.transmit.apiimpl.mapper.RoleApiMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/26 11:49
 */
@AllArgsConstructor
@Service
public class RoleApiServiceImpl extends ServiceImpl<RoleApiMapper, RoleApiEntity> implements RoleApiService {

    private final TransmitService transmitService;

    @Override
    public List<String> getRoleApiList(RoleQuery roleQuery) {

        //先获取角色菜单关系
        Set<String> apiIds = new HashSet<>();
        roleQuery.getRoles().forEach(x -> apiIds.addAll(baseMapper.getRoleApiCodes(x)));
        if (apiIds.size() > 0) {
            //获取菜单
            QueryWrapper<ApiEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id", apiIds);
            List<ApiEntity> apiList = transmitService.list(queryWrapper);
            //获取菜单权限作为角色权限
            return apiList.stream().map(ApiEntity::getPermissionCode).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
