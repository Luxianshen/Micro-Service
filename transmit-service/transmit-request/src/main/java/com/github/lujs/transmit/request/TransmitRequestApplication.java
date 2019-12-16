package com.github.lujs.transmit.request;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(basePackages = "com.github.lujs.*")
@ComponentScan("com.github.lujs.*")
@SpringBootApplication
public class TransmitRequestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransmitRequestApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
