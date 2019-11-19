package com.github.lujs.auth.apiimpl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lujs.auth.api.model.UserRole.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 用户角色mapper
 * @Author: lujs
 * @Data: 2019/5/18 18:35
 * @version: 1.0.0
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     *
     * @param userId
     * @return
     */
    List<String> getUserRoleList(@Param("userId") Long userId);
}
