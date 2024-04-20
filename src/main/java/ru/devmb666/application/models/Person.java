package ru.devmb666.application.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Insert your year of birth")
    @Min(value = 1900, message = "You're too old")
    @Max(value = 2024, message = "Incorrect year, should be less than 2025")
    private int date;

    public Person() {
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
