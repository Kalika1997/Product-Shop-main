package com.productshop.productservice.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "product")		//@Document -> TO tell our Springboot project that this POJO is MongoDB document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	
	@Id
	private String id;
	private String name;
	private String description;
	private BigDecimal price;

}


/*
//	its a good practise to make DTO (seprate model entities and DTOs)
	ideally we should not expose model entities to outside world bcs if in the future if I added some fields in model entity important to business logic but i don't want to show it to outside world

 */
