package com.dhfinalproject.inventoryservice;

import com.dhfinalproject.inventoryservice.model.Inventory;
import com.dhfinalproject.inventoryservice.repository.IInventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryserviceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(IInventoryRepository inventoryRepository){
//		return args -> {
//			Inventory i1 = new Inventory();
//			i1.setStockCode("Franziskaner");
//			i1.setStockQuantity(100);
//			Inventory i2 = new Inventory();
//			i2.setStockCode("Paulaner");
//			i2.setStockQuantity(0);
//			Inventory i3 = new Inventory();
//			i3.setStockCode("Augustiner");
//			i3.setStockQuantity(90);
//
//			inventoryRepository.save(i1);
//			inventoryRepository.save(i2);
//			inventoryRepository.save(i3);
//		};
//	}
}
