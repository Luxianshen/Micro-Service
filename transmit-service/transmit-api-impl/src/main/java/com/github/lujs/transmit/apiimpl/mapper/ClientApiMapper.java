package com.github.lujs.transmit.apiimpl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lujs.transmit.api.model.ApiEntityDto;
import com.github.lujs.transmit.api.model.ClientApiEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/26 11:50
 */
@Mapper
public interface ClientApiMapper extends BaseMapper<ClientApiEntity> {

    List<String> getClientApiCodes(@Param("agentId") String agentId);

    List<ApiEntityDto> getClientApiPermissions(@Param("agentId") Long agentId);
}
