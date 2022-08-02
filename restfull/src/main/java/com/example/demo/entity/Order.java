package com.example.demo.entity;

import java.util.HashMap;

//@JacksonXmlProperty(localName = "City")

public class Order {
    private String name;
    private String name2;
    private Integer id;
//    private HashMap<Integer, Product> productHashMap;


    public Order(String name, String name2, Integer id) {
        this.name = name;
        this.name2 = name2;
        this.id = id;
    }

    public Order(String price, Integer id, HashMap<Integer, Product> productHashMap) {
        this.name = price;
        this.id = id;
//        this.productHashMap = productHashMap;
    }

    public Order() {
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

//    public HashMap<Integer, Product> getProductHashMap() {
//        return productHashMap;
//    }
}
