package com.example.demo.Lager;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WarehouseConsumer {

    @KafkaListener(
            topics = "warehouse-001",
            groupId = "warehouses"
    )
    public void receive(String message) {
        System.out.println("Response: " + message);
    }
}
