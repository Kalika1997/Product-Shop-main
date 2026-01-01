package com.productshop;

import com.productshop.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notification-topic")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent){
        // TODO: WE CAN ADD EMAIL NOTIFICATION LOGIC HERE
      log.info("Notification received for order -> {}", orderPlacedEvent.getOrderNumber());
    }


}