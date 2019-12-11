package com.github.lujs.gatewayservice.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Describe: 路由断言实体
 * @Author: lujs
 * @Date: 2019/4/28 14:42
 * @Version: 1.0.0
 **/
@Data
public class GatewayPredicateDefinition {

    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

}
