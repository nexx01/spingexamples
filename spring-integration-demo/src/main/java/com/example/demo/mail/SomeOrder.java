package com.example.demo.mail;

import java.util.List;

public class SomeOrder {

    String name;

    List<String> ingredients;

    public SomeOrder() {
    }

    public SomeOrder(String name, List<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
