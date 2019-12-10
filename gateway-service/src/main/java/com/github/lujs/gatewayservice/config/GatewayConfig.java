package com.github.lujs.gatewayservice.config;

import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.github.lujs.gatewayservice.ExceptionHandler.JsonSentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/12/9 15:03
 */
//@Configuration
public class GatewayConfig {
    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfig(ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    @PostConstruct
    private void initCustomizedApis() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        /*rules.add(new GatewayFlowRule("transmit_route")
                .setCount(1)
                .setIntervalSec(1)
                .setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT)
                .setParamItem(new GatewayParamFlowItem()
                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_HEADER)
                        .setFieldName("X-Sentinel-Flag")
                        .setPattern("t1")
                        .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_EXACT)
                )
        );*/
        rules.add(new GatewayFlowRule("transmit_route")
                .setCount(2)
                .setIntervalSec(1)
                .setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT)
                .setParamItem(new GatewayParamFlowItem()
                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_HEADER)
                        .setFieldName("apiKey")
                        .setPattern("TESTPOST")
                        .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_EXACT)
                )
        );
        System.out.println(JSONUtil.toJsonStr(rules));
        GatewayRuleManager.loadRules(rules);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public JsonSentinelGatewayBlockExceptionHandler jsonSentinelGatewayBlockExceptionHandler() {
        // Register the block exception handler for Spring Cloud Gateway.
        return new JsonSentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }
}
