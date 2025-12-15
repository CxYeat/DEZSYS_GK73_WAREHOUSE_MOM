package com.example.demo.producer;

import com.example.demo.data.WarehouseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseProducer producer;

    public WarehouseController(WarehouseProducer producer) {
        this.producer = producer;
    }

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