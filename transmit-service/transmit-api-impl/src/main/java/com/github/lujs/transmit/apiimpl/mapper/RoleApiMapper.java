package com.github.lujs.transmit.apiimpl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lujs.transmit.api.model.RoleApiEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/26 11:50
 */
@Mapper
public interface RoleApiMapper extends BaseMapper<RoleApiEntity> {

    List<String> getRoleApiCodes(@Param("roleId") String roleId);
}
