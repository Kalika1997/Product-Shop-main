package com.productshop.inventoryservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {

    private String skuCode;             // we get it from Inventory model class from db
    private boolean isInStock;              // we check if if skuCode > 0 on that basis put true or false in service layer
}
