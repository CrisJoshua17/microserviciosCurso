package com.joshua.springcloud.mscv.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class MscvGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscvGatewayApplication.class, args);
	}

}
