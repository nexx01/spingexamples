package com.example.demo.entity;

public class Student {
    private int id;
    private String name ;
    private String surname;
    private String patrinomic;

    public Student(int id, String name, String surname, String patrinomic) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patrinomic = patrinomic;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatrinomic() {
        return patrinomic;
    }
}
