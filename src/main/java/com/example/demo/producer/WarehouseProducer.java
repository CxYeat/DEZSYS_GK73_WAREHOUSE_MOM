package com.example.demo.producer;

import com.example.demo.data.WarehouseData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Service zum Senden von WarehouseData-Nachrichten an Kafka.
 * <p>
 * Dieser Producer verwendet ein {@link KafkaTemplate} für {@link WarehouseData}-Objekte.
 * Die Nachrichten werden an dynamische Topics gesendet, die sich aus der Warehouse-ID zusammensetzen.
 */
@Service
public class WarehouseProducer {

    private final KafkaTemplate<String, WarehouseData> kafkaTemplate;

    /**
     * Konstruktor-Injektion des KafkaTemplates.
     *
     * @param kafkaTemplate KafkaTemplate für String-Keys und WarehouseData-Values
     */
    public WarehouseProducer(KafkaTemplate<String, WarehouseData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sendet ein WarehouseData-Objekt an das entsprechende Kafka-Topic.
     * <p>
     * Das Topic wird dynamisch erzeugt in der Form "warehouse-{warehouseID}".
     * Nach dem Senden wird eine Konsolen-Ausgabe mit Topic und Timestamp erzeugt.
     *
     * @param data das zu sendende WarehouseData-Objekt
     */
    public void sendWarehouse(WarehouseData data) {

        // DYNAMISCHES TOPIC
        String topic = "warehouse-" + data.getWarehouseID();

        kafkaTemplate.send(topic, data);

        System.out.println(
                "Warehouse gesendet an Topic " + topic +
                        " | Timestamp: " + data.getTimestamp()
        );
    }
}

