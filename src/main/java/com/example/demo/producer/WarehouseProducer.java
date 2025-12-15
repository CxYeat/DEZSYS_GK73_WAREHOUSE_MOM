package com.example.demo.producer;

import com.example.demo.data.WarehouseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WarehouseProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseProducer.class);
    private final KafkaTemplate<String, WarehouseData> kafkaTemplate;

    public WarehouseProducer(KafkaTemplate<String, WarehouseData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendWarehouse(WarehouseData data) {
        String topic = "warehouse-" + data.getWarehouseID();
        kafkaTemplate.send(topic, data);
        LOGGER.info("Sent warehouse data to topic {}: {}", topic, data);
    }
}

