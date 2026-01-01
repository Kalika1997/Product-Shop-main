package com.productshop.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//used to send/transfer data before saving into db
// same as product class from model package just that we dont need id here since this is not going to interact with DB, rather it is going to be used for transferring data (dto-> data transfer obj)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
	private String name;
	private String description;
	private BigDecimal price;
}
