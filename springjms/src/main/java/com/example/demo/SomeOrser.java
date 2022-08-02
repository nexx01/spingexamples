package com.example.demo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class SomeOrser implements Serializable {
    String name;
    String surname;

    public SomeOrser() {
    }

    public SomeOrser(String name, String surname) {
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
}
