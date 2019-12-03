package com.github.lujs.transmit.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lujs.transmit.api.model.ApiEntityDto;
import com.github.lujs.transmit.api.model.ClientApiEntity;

import java.util.List;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/12/3 10:20
 */
public interface ClientApiService extends IService<ClientApiEntity> {

    List<String> getClientApiList(String agentId);

    List<ApiEntityDto> getClientApiPermissions(Long agentId);

}
