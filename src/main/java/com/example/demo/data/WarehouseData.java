package com.example.demo.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@JacksonXmlRootElement(localName = "warehouse")
public class WarehouseData {

    private String warehouseID;
    private String warehouseName;
    private String warehouseAddress;
    private String warehousePostalCode;
    private String warehouseCity;
    private String warehouseCountry;
    private String timestamp;
    private List<ProductData> productData;

    /**
     * Constructor
     */
    public WarehouseData() {
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    /**
     * Methods
     */
    @Override
    public String toString() {
        String info = String.format("Warehouse Info: ID = %s, timestamp = %s", warehouseID, timestamp );
        return info;
    }
}
