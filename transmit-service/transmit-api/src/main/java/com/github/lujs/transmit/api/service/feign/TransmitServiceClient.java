package com.github.lujs.transmit.api.service.feign;

import com.github.lujs.auth.api.model.Role.RoleQuery;
import com.github.lujs.transmit.api.service.feign.hystrix.TransmitServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 用户权限服务
 * @Author lujs
 * @Date 2019/11/13 15:17
 */
@FeignClient(name = "transmit-service",fallback = TransmitServiceHystrix.class)
public interface TransmitServiceClient {

    /**
     * 获取用户权限列表
     */
    @PostMapping("/transmit/api/getRoleApiList")
    List<String> getRoleApiList(RoleQuery roleQuery);

    /**
     * 获取客户端权限列表
     */
    @PostMapping("/transmit/api/getClientApiList")
    List<String> getClientApiList(@RequestParam("clientId") Long clientId);
}
