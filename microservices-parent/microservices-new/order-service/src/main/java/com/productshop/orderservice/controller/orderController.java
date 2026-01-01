package com.productshop.orderservice.controller;

import com.productshop.orderservice.dto.OrderRequest;
import com.productshop.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class orderController {

    private final OrderService orderService;


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//    public String placeOrder(@RequestBody OrderRequest orderRequest){
//        orderService.placeOrder(orderRequest);
//        return "Order placed Successfully";
//    }


    // using same returntype as original method -placeOrder (String) + same Method signature
//    public String fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
//        return "OOPS !! something went wrong, pls try after some time (Inventory service might be down ) this is a fallback method";
//    }



//    timeout needs different method signature of type CompletableFuture<>


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")        // this will make an async call(this call will go in new Thread)  therefore we need to change the method dataType to CompletableFuture<T>
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
        //this placeOrder method will now execute in different thread  (async call)
        // when time-limit is reached(3sec as in properties file) then timeout exception will be thrown
//        TODO -> catch timeout exception and throw proper error message
        return CompletableFuture.supplyAsync(()->
                orderService.placeOrder(orderRequest)
        );

    }


    // using same return type as original method -placeOrder (String) + same Method signature
    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
        System.out.println("exception in orderController fallback method" +runtimeException);
        return  CompletableFuture.supplyAsync(()->"OOPS !! something went wrong, pls try after some time ");
    }
}
