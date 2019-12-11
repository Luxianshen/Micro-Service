package com.github.lujs.user.apiimpl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lujs.user.api.model.UserClient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 用户mapper接口
 * @Author: lujs
 * @Data: 2019/5/3 15:15
 * @version: 1.0.0
 */
@Mapper
public interface UserClientMapper extends BaseMapper<UserClient> {

    /**
     * 根据客户端ID获取客户端信息
     * @param agentId  客户端ID
     */
    UserClient getClientByAgentId(@Param("agentId") String agentId);
}
