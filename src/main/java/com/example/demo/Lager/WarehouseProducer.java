package com.example.demo.Lager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WarehouseProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "warehouse-001";

    public WarehouseProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendWarehouseData(String warehouseJson) {
        kafkaTemplate.send(TOPIC, warehouseJson);
        System.out.println("Lagerdaten gesendet: " + warehouseJson);
    }

}
