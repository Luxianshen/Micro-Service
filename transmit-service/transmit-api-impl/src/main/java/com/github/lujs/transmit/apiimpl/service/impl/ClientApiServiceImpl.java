package com.github.lujs.transmit.apiimpl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.transmit.api.model.ApiEntityDto;
import com.github.lujs.transmit.api.model.ClientApiEntity;
import com.github.lujs.transmit.api.service.ClientApiService;
import com.github.lujs.transmit.apiimpl.mapper.ClientApiMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/26 11:49
 */
@Service
public class ClientApiServiceImpl extends ServiceImpl<ClientApiMapper, ClientApiEntity> implements ClientApiService {

    @Override
    public List<String> getClientApiList(Long clientId) {

        List<String> clientApiList = baseMapper.getClientApiCodes(clientId);
        if (null != clientApiList) {
            return clientApiList;
        }
        return new ArrayList<>();
    }

    @Override
    public List<ApiEntityDto> getClientApiPermissions(Long agentId) {
        return baseMapper.getClientApiPermissions(agentId);
    }
}
