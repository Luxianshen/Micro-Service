package com.github.lujs.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: MyBatisPlus配置类
 * @Author lujs
 * @Date 2019/11/11 15:21
 */
@Configuration
@MapperScan({"com.github.lujs.*.*.mapper", "cn.github.lujs.*.*.*.mapper"})
public class MyBatisPlusConfig {

    private final static Logger logger = LoggerFactory.getLogger(MyBatisPlusConfig.class);

    /**
     *配置分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        logger.debug("注册分页插件");
        return new PaginationInterceptor();
    }
}
