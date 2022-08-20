package com.example.demo;

import lombok.Data;

import java.io.Serializable;


@Data
public class SomeOrder implements Serializable {

    String id;
    String name;
    String surname;

    public SomeOrder() {
    }

    public SomeOrder(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
