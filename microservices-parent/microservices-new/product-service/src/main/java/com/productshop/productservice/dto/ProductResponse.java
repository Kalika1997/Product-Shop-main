package com.productshop.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// retrieving data from db (response)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
	private String id;
	private String name;
	private String description;
	private BigDecimal price;

}
