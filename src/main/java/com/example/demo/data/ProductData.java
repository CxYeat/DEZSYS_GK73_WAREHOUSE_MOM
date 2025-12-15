package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Alles neu
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductData {

    private String productID;
    private String productName;
    private String productCategory;
    private int productQuantity;
    private String productUnit;

}
