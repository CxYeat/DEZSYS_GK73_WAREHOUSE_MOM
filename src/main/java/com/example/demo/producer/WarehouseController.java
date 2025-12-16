package com.example.demo.producer;

import com.example.demo.data.WarehouseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * REST-Controller für Warehouse-Daten.
 * <p>
 * Dieser Controller stellt Endpunkte zur Verfügung, um WarehouseData-Objekte
 * an Kafka zu senden. Vor dem Versand wird ein Timestamp gesetzt, falls dieser fehlt.
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseProducer producer;

    /**
     * Konstruktor-Injektion des WarehouseProducers.
     *
     * @param producer der Producer-Service, der die Daten an Kafka sendet
     */
    public WarehouseController(WarehouseProducer producer) {
        this.producer = producer;
    }

    /**
     * POST-Endpunkt zum Senden von WarehouseData.
     * <p>
     * Wenn das empfangene WarehouseData-Objekt noch keinen Timestamp besitzt,
     * wird dieser beim Empfang gesetzt. Anschließend wird das Objekt
     * an das entsprechende Kafka-Topic gesendet.
     *
     * @param data WarehouseData-Objekt aus dem Request-Body
     * @return ResponseEntity mit dem gesendeten WarehouseData-Objekt
     */
    @PostMapping("/send")
    public ResponseEntity<WarehouseData> sendWarehouse(@RequestBody WarehouseData data) {
        // Timestamp setzen beim Versand
        if (data.getTimestamp() == null) {
            data.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        }
        producer.sendWarehouse(data);
        return ResponseEntity.ok(data);
    }
}