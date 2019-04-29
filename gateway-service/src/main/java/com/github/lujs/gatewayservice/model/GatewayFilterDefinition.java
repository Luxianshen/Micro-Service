package com.github.lujs.gatewayservice.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Describe: 过滤器实体
 * @Author: lujs
 * @Date: 2019/4/28 14:30
 * @Version: 1.0.0
 **/
public class GatewayFilterDefinition {

    /**
     * filter名称
     */
    private String name;

    /**
     * 储存路由规则
     */
    private Map<String,String> args = new LinkedHashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}
