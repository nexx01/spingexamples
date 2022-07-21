package com.example.demo.entity;


public class Product {

    private Integer id;
    private Integer name;
    private Integer price;

    public Product(Integer id, Integer name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
