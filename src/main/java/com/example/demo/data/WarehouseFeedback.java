package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseFeedback {

    private String feedbackId;
    private String warehouseID;
    private String source;   // SENDER oder ZENTRALE
    private String status;   // SENT, SUCCESS, ERROR
    private String timestamp;
    private String message;

    public static WarehouseFeedback fromSender(String warehouseID) {
        return new WarehouseFeedback(
                UUID.randomUUID().toString(),
                warehouseID,
                "SENDER",
                "SENT",
                Instant.now().toString(),
                "Warehouse data sent"
        );
    }

    public static WarehouseFeedback fromCentral(String warehouseID) {
        return new WarehouseFeedback(
                UUID.randomUUID().toString(),
                warehouseID,
                "ZENTRALE",
                "SUCCESS",
                Instant.now().toString(),
                "Warehouse data processed"
        );
    }
}
