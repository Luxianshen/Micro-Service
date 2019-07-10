package com.github.lujs.config;

import com.github.lujs.intercepter.RestTemplateUserContextInterceptor;
import com.github.lujs.intercepter.UserContextInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Describe: 拦截器配置
 * @Author: lujs
 * @Date: 2019/4/29 13:58
 * @Version: 1.0.0
 **/

@Configuration
@EnableWebMvc
public class CommonConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //添加自定义用户拦截器
        registry.addInterceptor(new UserContextInterceptor());
    }

    /**
     * RestTemplate 拦截器，在发送请求前设置鉴权的用户上下文信息
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new RestTemplateUserContextInterceptor());
        return restTemplate;
    }

}
