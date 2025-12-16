package com.example.demo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka-Consumer für Feedback-Nachrichten von Warehouses.
 * <p>
 * Dieser Service lauscht auf das Topic "feedback-warehouse" und verarbeitet
 * eingehende String-Nachrichten. Die empfangenen Nachrichten werden über SLF4J geloggt.
 */
@Service
public class WarehouseFeedbackConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseFeedbackConsumer.class);

    /**
     * Empfang von Feedback-Nachrichten.
     * <p>
     * Der KafkaListener ist an das Topic "feedback-warehouse" gebunden,
     * nutzt die Gruppe "warehouse-feedback-group" und die ContainerFactory
     * "stringKafkaListenerContainerFactory", die in der KafkaConfig definiert ist.
     *
     * @param message die empfangene Feedback-Nachricht als String
     */
    @KafkaListener(
            topics = "feedback-warehouse",
            groupId = "warehouse-feedback-group",
            containerFactory = "stringKafkaListenerContainerFactory"
    )
    public void receiveFeedback(String message) {
        LOGGER.info("Feedback received: {}", message);
    }
}
