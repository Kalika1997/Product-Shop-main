package com.productshop.orderservice.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_orders")
@Data
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String orderNumber;						// will be setting random no. in service layer
	
	@OneToMany(cascade = CascadeType.ALL)								// one Order can have multiple OrderLineItems
	private List<OrderLineItems> orderLineItemsList;			// will set this in service layer by getting it from dto

}
