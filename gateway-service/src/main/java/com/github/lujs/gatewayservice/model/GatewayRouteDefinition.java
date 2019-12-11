package com.github.lujs.gatewayservice.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe: 路由实体
 * @Author: lujs
 * @Date: 2019/4/28 14:41
 * @Version: 1.0.0
 **/
@Data
public class GatewayRouteDefinition {

    /**
     * 路由的Id
     */
    private String id;

    /**
     * 路由断言集合配置
     */
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();

    /**
     * 路由过滤器集合配置
     */
    private List<GatewayFilterDefinition> filters = new ArrayList<>();

    /**
     * 路由规则转发的目标uri
     */
    private String uri;

    /**
     * 路由执行的顺序
     */
    private int order = 0;

}
