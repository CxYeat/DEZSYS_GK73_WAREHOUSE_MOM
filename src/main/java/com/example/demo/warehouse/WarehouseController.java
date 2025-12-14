package com.example.demo.warehouse;

import com.example.demo.model.WarehouseData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;

import java.util.List;

@CrossOrigin(origins = "*") // erlaubt alle Quellen
@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService service;


    @GetMapping("/")
    public String warehouseMain() {
        return "index";
    }

    @GetMapping(value="/warehouse/{inID}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public WarehouseData warehouseJSON(@PathVariable String inID ) {
        return service.getWarehouseData( inID );
    }

    @GetMapping(value ="/warehouse/{inID}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public WarehouseData warehouseXML(@PathVariable String inID ) {
        return service.getWarehouseData( inID );
    }

    @GetMapping(value = "/warehouse/all/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WarehouseData> warehouseAll() {
        return service.getAllWarehouses();
    }

    @PostMapping(value = "/warehouse/send")
    public String send() {
        return "";
    }
}