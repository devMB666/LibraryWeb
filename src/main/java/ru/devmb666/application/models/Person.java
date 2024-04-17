package ru.devmb666.application.models;

public class Person {
    private int id;
    private String name;
    private int date;

    public Person(String name, int dateOfBirth) {
        this.name = name;
        this.date = dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getDate() {
        return date;
    }
}
