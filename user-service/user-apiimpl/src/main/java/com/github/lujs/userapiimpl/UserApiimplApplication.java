package com.github.lujs.userapiimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserApiimplApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApiimplApplication.class, args);
	}

}
