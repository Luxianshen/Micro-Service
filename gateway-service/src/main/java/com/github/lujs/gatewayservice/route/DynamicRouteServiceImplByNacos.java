package com.github.lujs.gatewayservice.route;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @Describe: nacos监听更新路由
 * @Author: lujs
 * @Date: 2019/4/28 15:27
 * @Version: 1.0.0
 **/
@Component
public class DynamicRouteServiceImplByNacos {

    private static final String DATA_ID = "test";
    private static final String GROUP = "group";
    private static final String URL = "127.0.0.1:8848";

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    public DynamicRouteServiceImplByNacos() {

        dynamicRouteByNacosListener(DATA_ID, GROUP);
    }

    /**
     * 监听自动触发
     *
     * @param dataId config id
     * @param group  config group
     */
    private void dynamicRouteByNacosListener(String dataId, String group) {
        try {
            //注册配置
            ConfigService configService = NacosFactory.createConfigService(URL);
            //设置监听事件
            configService.addListener(dataId, group, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    //获取到配置，对路由进行更新
                    RouteDefinition definition = JSON.parseObject(configInfo, RouteDefinition.class);
                    dynamicRouteService.update(definition);
                }
            });
        } catch (NacosException e) {
            System.err.println("路由更新出错：" + e.getErrMsg());
        }
    }
}
