package com.github.lujs.transmit.api.service.feign.hystrix;

import com.github.lujs.auth.api.model.Role.RoleQuery;
import com.github.lujs.transmit.api.service.feign.TransmitServiceClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户权限服务熔断
 * @Author lujs
 * @Date 2019/11/13 15:20
 */
@Component
public class TransmitServiceHystrix implements TransmitServiceClient {

    @Override
    public List<String> getRoleApiList(RoleQuery roleQuery) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getClientApiList(Long clientId) {
        return new ArrayList<>();
    }
}
