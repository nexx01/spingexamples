package com.example.demo.entity;

import java.util.HashMap;

//@JacksonXmlProperty(localName = "City")

public class Order {
    private String price;
    private Integer id;
//    private HashMap<Integer, Product> productHashMap;

    public Order(String price, Integer id, HashMap<Integer, Product> productHashMap) {
        this.price = price;
        this.id = id;
//        this.productHashMap = productHashMap;
    }

    public Order() {
    }

    public String getPrice() {
        return price;
    }

    public Integer getId() {
        return id;
    }

//    public HashMap<Integer, Product> getProductHashMap() {
//        return productHashMap;
//    }
}
