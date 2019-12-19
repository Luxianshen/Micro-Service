package com.github.lujs.config;

import com.github.lujs.intercepter.ApiContextInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Describe: 拦截器配置
 * @Author: lujs
 * @Date: 2019/4/29 13:58
 * @Version: 1.0.0
 **/

@Configuration
@EnableWebMvc
public class CommonConfiguration implements WebMvcConfigurer {

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //添加自定义用户拦截器
        registry.addInterceptor(new ApiContextInterceptor());
    }

}
