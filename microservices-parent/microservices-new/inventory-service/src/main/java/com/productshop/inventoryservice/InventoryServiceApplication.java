package com.productshop.inventoryservice;

import com.netflix.discovery.EurekaClient;
import com.productshop.inventoryservice.model.Inventory;
import com.productshop.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;


@SpringBootApplication
//@EnableEurekaClien					// for some reason this annotation is not getting picked up from dependency, hence using eureka client via Autowired
@EnableDiscoveryClient
public class InventoryServiceApplication {

//	@Autowired
//	@Lazy
//	private EurekaClient eurekaClient;
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iPhone_13");
			inventory1.setQuantity(100);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iPhone_14");
			inventory2.setQuantity(0);

			Inventory inventory3 = new Inventory();
			inventory3.setSkuCode("POCO_Phone");
			inventory3.setQuantity(200);

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
			inventoryRepository.save(inventory3);
		};
	}
}
