package com.github.lujs.gatewayservice.filter;

import com.github.lujs.Exception.PermissionException;
import com.github.lujs.Exception.status.PermissionStatusCode;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.gatewayservice.util.IpUtil;
import com.github.lujs.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @Description: 权限认证
 * @Author: lujs
 * @Data: 2019/4/28 23:22
 * @version: 1.0.0
 */

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取对应的url
        Map<String, String> userMap;
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();
        HttpHeaders header = request.getHeaders();

        String token = header.getFirst(CommonConstant.HEADER_AUTH);
        String reqHost = IpUtil.getIpAddress(request);

        if (StringUtils.isEmpty(header.getFirst(CommonConstant.API_REQ))) {
            userMap = JwtUtil.validateToken(token, true);
        } else {
            userMap = JwtUtil.validateToken(token, false);
        }

        ServerHttpRequest.Builder mutate = request.mutate();
        //检验是否本系统用户
        if (StringUtils.isNotEmpty(userMap.get(CommonConstant.HEADER_PARAM_USER))) {
            //携带用户信息 访问信息
            mutate.header(CommonConstant.REQUEST_ID_HEADER, userMap.get(CommonConstant.HEADER_PARAM_ID));
            mutate.header(CommonConstant.REQUEST_NAME_HEADER, userMap.get(CommonConstant.HEADER_PARAM_USER));
            mutate.header(CommonConstant.REQUEST_HOST_HEADER, reqHost);
        } else {
            throw new PermissionException(PermissionStatusCode.TOKEN_ILLEGAL);
        }
        ServerHttpRequest buildRequest = mutate.build();
        return chain.filter(exchange.mutate().request(buildRequest).build());
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
