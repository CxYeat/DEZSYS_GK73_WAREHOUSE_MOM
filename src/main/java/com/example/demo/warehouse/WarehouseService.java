package com.example.demo.warehouse;

import com.example.demo.model.WarehouseData;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService {
	
	public String getGreetings( String inModule ) {
        return "Greetings from " + inModule;
    }

    public WarehouseData getWarehouseData(String inID ) {
    	WarehouseSimulation simulation = new WarehouseSimulation();
        return simulation.getData( inID );
    }

    // WarehouseService
    public List<WarehouseData> getAllWarehouses() {
        WarehouseSimulation simulation = new WarehouseSimulation();
        return simulation.getAllWarehouses();
    }
}