package com.productshop.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

//first need to add webflux dependency in pom
@Configuration                      // since bean is to be instantiated
public class WebClientConfig {

    @Bean
    @LoadBalanced                   // since it may find multiple instances of inventory-service
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }


}
