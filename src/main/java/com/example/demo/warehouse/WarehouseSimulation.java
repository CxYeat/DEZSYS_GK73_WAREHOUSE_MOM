package com.example.demo.warehouse;


import com.example.demo.model.ProductData;
import com.example.demo.model.WarehouseData;

import java.util.*;

public class WarehouseSimulation {

    private final Map<String, WarehouseData> warehouses = new HashMap<>();

    public WarehouseSimulation() {
        warehouses.put("001", createWarehouse("001", "Linz Bahnhof",
                "Rechte Nordbhangasse 5", "4020", "Linz", "Österreich"));
        warehouses.put("002", createWarehouse("002", "Wien Hafen",
                "Donauhafenstraße 12", "1020", "Wien", "Österreich"));
        warehouses.put("003", createWarehouse("003", "Grazer Zentrum",
                "Herrengasse 7", "8010", "Graz", "Österreich"));
        warehouses.put("004", createWarehouse("004", "Salzburg Bahnhof",
                "Bahnhofstraße 1", "5020", "Salzburg", "Österreich"));
    }

    /**
     * Hilfsmethode zum Erstellen eines Warehouses mit vollständigen Daten.
     */
    private WarehouseData createWarehouse(String id, String name,
                                          String address, String postal, String city, String country) {
        WarehouseData data = new WarehouseData();
        data.setWarehouseID(id);
        data.setWarehouseName(name);
        data.setWarehouseAddress(address);
        data.setWarehousePostalCode(postal);
        data.setWarehouseCity(city);
        data.setWarehouseCountry(country);
        return data;
    }

    private double getRandomDouble( int inMinimum, int inMaximum ) {
        double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum;
        double rounded = Math.round(number * 100.0) / 100.0;
        return rounded;

    }
    private int getRandomInt( int inMinimum, int inMaximum ) {
        double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum;
        Long rounded = Math.round(number);
        return rounded.intValue();
    }
    private ProductData createProduct() {
        Random random = new Random();
        Map<String, String> products = new HashMap<>();
        products.put("Bio Orangensaft", "Getraenk");
        products.put("Apfelsaft naturtrüb", "Getraenk");
        products.put("Mineralwasser Classic", "Getraenk");
        products.put("Bio Butter", "Milchprodukte");
        products.put("Naturjoghurt", "Milchprodukte");
        products.put("Frischkäse", "Milchprodukte");
        products.put("Türkische Salami", "Fleisch");
        products.put("Bosnisches Sudzuk", "Fleisch");
        products.put("Hähnchenbrustfilet", "Fleisch");
        products.put("Rinderhackfleisch", "Fleisch");
        products.put("Persil Discs Color", "Waschmittel");
        products.put("Ariel Waschmittel Color", "Waschmittel");
        products.put("Lenor Weichspüler", "Waschmittel");
        products.put("Frosch Waschmittel", "Waschmittel");
        products.put("Schokolade Zartbitter", "Süßigkeiten");
        products.put("Gummibärchen", "Süßigkeiten");

        List<String> keys = new ArrayList<>(products.keySet());
        String name = keys.get(random.nextInt(keys.size()));
        String category = products.get(name);

        int quantity = getRandomInt(100, 5000);
        String unit;
        switch (category) {
            case "Getraenk": unit = "Packung 1L"; break;
            case "Milchprodukte": unit = "Packung 250g"; break;
            case "Fleisch": unit = "Packung 500g"; break;
            case "Waschmittel": unit = "Packung 3KG"; break;
            case "Süßigkeiten": unit = "Packung 200g"; break;
            default: unit = "Stück"; break;
        }

        return new ProductData(
                "00-" + getRandomInt(100000, 999999),
                name,
                category,
                quantity,
                unit
        );
    }

    private List<ProductData> createProducts(int count) {
        List<ProductData> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(createProduct());
        }
        return list;
    }

    public WarehouseData getData(String inID) {
        WarehouseData data = new WarehouseData();
        data.setWarehouseID(inID);
        WarehouseData daten = warehouses.getOrDefault(
                inID,
                createWarehouse("000", "Unbekanntes Lager", "Unbekannte Adresse", "0000", "Unbekannte Stadt", "Unbekanntes Land")
        );
        data.setWarehouseName(daten.getWarehouseName());
        data.setWarehouseAddress(daten.getWarehouseAddress());
        data.setWarehousePostalCode(daten.getWarehousePostalCode());
        data.setWarehouseCity(daten.getWarehouseCity());
        data.setWarehouseCountry(daten.getWarehouseCountry());
        data.setProductData(createProducts(5));
        return data;
    }
    public List<WarehouseData> getAllWarehouses() {
        List<WarehouseData> list = new ArrayList<>();
        for (WarehouseData data : warehouses.values()) {
            data.setProductData(createProducts(5));
            list.add(data);
        }
        return list;
    }
}