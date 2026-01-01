package com.productshop.orderservice.service;


import com.productshop.orderservice.dto.InventoryResponse;
//import com.productshop.inventoryservice.dto.InventoryResponse;
import com.productshop.orderservice.dto.OrderLineItemsDto;
import com.productshop.orderservice.dto.OrderRequest;
import com.productshop.orderservice.event.OrderPlacedEvent;
import com.productshop.orderservice.model.Order;
import com.productshop.orderservice.model.OrderLineItems;
import com.productshop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    @Autowired
    private final WebClient.Builder webClientBuilder;

    //    injecting kafka template so that we can push/publish orderNo. to topic after saving it to db and before returning from the method
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest){                // OrderRequest have -> List<OrderLineItemsDto> orderLineItemsDtoList
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
//        System.out.println("from order service  -> order  = "+ order);

//        as of now we have dtoList of orderlineItems form orderRequest(also a dto), so we need to convert it into actual model (order) before saving to db
        List<OrderLineItems> orderLineItems=  orderRequest.getOrderLineItemsDtoList()
                .stream()
//                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).toList();
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);
//        System.out.println("AGAIN from order service  -> order  = "+ order);

        // first get all the sku-codes from n no. of orderLineItems into a list and check if all the skuCodes are present in the Inventory of not (we have an end-point in InveteryServiceController that takes list of sku-codes and returns the list of InventoryResponse object which have 2 data fields (String sku-code and boolean isSkuInStock)
        List<String> skuCodes= order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();





//        //call inventory  service  to check if order is present in stock (if order is in stock then place order -> save to db ELSE throw some sort of error saying product not available )
// TODO: send qty along with skuCode, so that it can be checked in inventory if that much qty is present or not

        InventoryResponse[] inventoryResponseArray= webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())    // this will make our uri look like this -> http://localhost:8082/api/inventory?skuCode=iPhone_13&skuCode=iPhone_14
                        .retrieve()
                            .bodyToMono(InventoryResponse[].class)          // to read/parse data from webClient we use bodyToMono
                                .block();               //this block will allow it for synchronous call  otherwise by default it(webClientBuilder) was supposed to be Asynchronous call
//        System.out.println("\n-------####################--------------------\n"+Arrays.stream(inventoryResponseArray).toList());

        boolean isAllProductsInStock= Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);
//inventoryResponse -> inventoryResponse.isInStock
        if(inventoryResponseArray.length < skuCodes.size())
            throw new IllegalArgumentException("Such Item is not present");
        else if(isAllProductsInStock){
            orderRepository.save(order);
            kafkaTemplate.send("notification-topic", new OrderPlacedEvent(order.getOrderNumber()));
            return "Order placed Successfully !!";
        }
        else{
//            System.out.println("out of stock ");
            return "out of stock";
        }

//
//        orderRepository.save(order);
//        return "Order placed Successfully";
    }






    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

//        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
