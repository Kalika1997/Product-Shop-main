package com.productshop.orderservice;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
//@EnableEurekaClien					// for some reason this annotation is not getting picked up from dependency, hence using eureka client via Autowired
@EnableDiscoveryClient
public class OrderServiceApplication {

	@Autowired
	@Lazy
	private EurekaClient eurekaClient;
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
