package com.example.demo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WarehouseFeedbackConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseFeedbackConsumer.class);

    @KafkaListener(
            topics = "feedback-warehouse",
            groupId = "warehouse-feedback-group",
            containerFactory = "stringKafkaListenerContainerFactory"
    )
    public void receiveFeedback(String message) {
        LOGGER.info("Feedback received: {}", message);
    }
}
