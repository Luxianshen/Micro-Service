package com.github.lujs.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.codec.ServerCodecConfigurer;

/**
 * @Author: lujs
 * @Date: 2019/4/28 14:08
 * @Version: 1.0.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.github.lujs.*")
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

}
