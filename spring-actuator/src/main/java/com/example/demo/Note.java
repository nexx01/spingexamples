package com.example.demo;

import lombok.Data;

import java.util.Date;

@Data
public class Note {

    private Date time = new Date();
    String ddd;

    public Note(String ddd) {
        this.ddd = ddd;
    }
}
