package com.github.lujs.gatewayservice.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Describe: 过滤器实体
 * @Author: lujs
 * @Date: 2019/4/28 14:30
 * @Version: 1.0.0
 **/
@Data
public class GatewayFilterDefinition {

    /**
     * filter名称
     */
    private String name;

    /**
     * 储存路由规则
     */
    private Map<String,String> args = new LinkedHashMap<>();

}
