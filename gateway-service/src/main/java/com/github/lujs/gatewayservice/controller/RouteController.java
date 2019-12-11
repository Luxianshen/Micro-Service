package com.github.lujs.gatewayservice.controller;

import com.github.lujs.gatewayservice.model.GatewayPredicateDefinition;
import com.github.lujs.gatewayservice.model.GatewayRouteDefinition;
import com.github.lujs.gatewayservice.route.DynamicRouteServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @Describe: 路由控制层
 * @Author: lujs
 * @Date: 2019/4/28 14:09
 * @Version: 1.0.0
 **/

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    /**
     * @param gRDefinition
     * @return String
     * @Describe: 路由新增方法
     */
    @PostMapping("/add")
    public String add(@RequestBody GatewayRouteDefinition gRDefinition) {
        try {
            RouteDefinition routeDefinition = assembleRouteDefinition(gRDefinition);
            dynamicRouteService.add(routeDefinition);
        } catch (Exception e) {
            System.out.println("转换出错了！");
            return null;
        }
        return "success";
    }

    /**
     * @param gRDefinition
     * @return String
     * @Describe: 路由更新方法
     */
    @PostMapping("/update")
    public String update(@RequestBody GatewayRouteDefinition gRDefinition) {
        try {
            RouteDefinition routeDefinition = assembleRouteDefinition(gRDefinition);
            dynamicRouteService.update(routeDefinition);
        } catch (Exception e) {
            System.out.println("转换出错了！");
            return null;
        }
        return "success";
    }

    /**
     * @param id 路由id
     * @return String
     * @Describe: 路由更新方法
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {

        dynamicRouteService.delete(id);
        return "success";
    }

    /**
     * 自定义路由实体转换
     *
     * @return RouteDefinition
     */
    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gRDefinition) {

        RouteDefinition routeDefinition = new RouteDefinition();
        List<PredicateDefinition> pdList = Lists.newArrayList();
        routeDefinition.setId(gRDefinition.getId());
        List<GatewayPredicateDefinition> gpdList = gRDefinition.getPredicates();
        for (GatewayPredicateDefinition gpd : gpdList) {
            PredicateDefinition pd = new PredicateDefinition();
            pd.setArgs(gpd.getArgs());
            pd.setName(gpd.getName());
            pdList.add(pd);
        }

        routeDefinition.setPredicates(pdList);
        URI uri = UriComponentsBuilder.fromHttpUrl(gRDefinition.getUri()).build().toUri();
        routeDefinition.setUri(uri);

        return routeDefinition;
    }

}
