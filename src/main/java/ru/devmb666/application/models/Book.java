package ru.devmb666.application.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.Year;

public class Book {
    private int id;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Author name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Author name should be between 2 and 30 characters")
    private String author;
    @Max(value = 2024, message = "Incorrect year, should be less than 2025")
    private Integer year;

    public Book() {
    }

    public Book(int id, String name, String author, Integer year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }
}
