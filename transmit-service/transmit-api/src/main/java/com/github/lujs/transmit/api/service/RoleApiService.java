package com.github.lujs.transmit.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lujs.auth.api.model.Role.RoleQuery;
import com.github.lujs.transmit.api.model.RoleApiEntity;

import java.util.List;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/26 11:49
 */
public interface RoleApiService extends IService<RoleApiEntity> {

    List<String> getRoleApiList(RoleQuery roleQuery);
}
