package com.example.customerApi.Services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "customer-updates", groupId = "customer-group")
    public void listen(String message) {
        System.out.println("Received Kafka message: " + message);
    }
}
