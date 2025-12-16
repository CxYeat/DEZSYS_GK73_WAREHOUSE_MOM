package com.example.demo.producer;

import com.example.demo.data.WarehouseData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WarehouseProducer {

    private final KafkaTemplate<String, WarehouseData> kafkaTemplate;

    public WarehouseProducer(KafkaTemplate<String, WarehouseData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendWarehouse(WarehouseData data) {

        // 🔹 DYNAMISCHES TOPIC
        String topic = "warehouse-" + data.getWarehouseID();

        kafkaTemplate.send(topic, data);

        System.out.println(
                "Warehouse gesendet an Topic " + topic +
                        " | Timestamp: " + data.getTimestamp()
        );
    }
}

