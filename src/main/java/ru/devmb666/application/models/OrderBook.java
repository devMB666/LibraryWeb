package ru.devmb666.application.models;

import org.hibernate.validator.constraints.UniqueElements;

public class OrderBook {
    private int person_id;
    @UniqueElements(message = "unique")
    private int book_id;
    private String authorName;
    private String bookName;

    private String personName;

    public OrderBook(int person_id, int book_id, String authorName, String bookName, String personName) {
        this.person_id = person_id;
        this.authorName = authorName;
        this.book_id = book_id;
        this.bookName = bookName;
        this.personName = personName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public OrderBook() {
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
