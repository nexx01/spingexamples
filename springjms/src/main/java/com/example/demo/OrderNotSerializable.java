package com.example.demo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = OrderNotSerializable.class, name = "AbstractContacts"),
//})
public class OrderNotSerializable{
    String nameCus;
    String surname;

    public OrderNotSerializable() {
    }

    public OrderNotSerializable(String nameCus) {
        this.nameCus = nameCus;
    }



    public OrderNotSerializable(String nameCus, String surname) {
        this.nameCus = nameCus;
        this.surname = surname;
    }

    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
