package com.productshop.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productshop.productservice.dto.ProductRequest;
import com.productshop.productservice.dto.ProductResponse;
import com.productshop.productservice.model.Product;
import com.productshop.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;			// constructor injection (via lombok

	public void createProduct(ProductRequest productRequest) {
		System.out.println("in createProduct - service");
		Product product = Product.builder()
				  .name(productRequest.getName())
				  .description(productRequest.getDescription())
				  .price(productRequest.getPrice())
				  .build();
		
		productRepository.save(product);		
		log.info("Product {} is saved ",product.getName());
	}
	
	public List<ProductResponse> getAllProducts(){
		List<Product> products=  productRepository.findAll();
		return products.stream().map(product -> mapToProductResponse(product)).toList();		
//		return products.stream().map(this::mapToProductResponse).toList();				// this can be used to as a method refrence 
	}
	
	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
}
