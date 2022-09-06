package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SomeEntity {

    @Id
    Integer integer;

    String field;

    public SomeEntity() {
    }

    public SomeEntity(Integer integer, String field) {
        this.integer = integer;
        this.field = field;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
