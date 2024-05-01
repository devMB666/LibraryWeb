package ru.devmb666.application.dao;


import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.devmb666.application.models.Book;
import ru.devmb666.application.models.Person;

import java.util.List;

@Component
public class BooksDAO {
    //private final JdbcTemplate jdbcTemplate;

    private final EntityManager entityManager;

    @Autowired
    public BooksDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        //this.jdbcTemplate = jdbcTemplate;
    }


    public boolean isBookFree(int bookId) {
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, bookId);
        if(book.getOwner() == null){
            return true;
        }
        else return false;
    }

    @Transactional
    public void appointBook(int person_id, int book_id){
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, book_id);
        Person person = session.get(Person.class, person_id);
        book.setOwner(person);
        person.getBooks().add(book);
    }

//    public List<Book> getListOfBooks(){
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Book getBookById(int id){
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id)
//                .stream().findAny().orElse(null);
//    }
//
//    public void saveBook(Book book){
//        jdbcTemplate.update("INSERT INTO Book (name, author, year) VALUES (?,?,?)", book.getName(), book.getAuthor(), book.getYear());
//    }
//
//    public void updateBook(int id, Book updatedBook){
//        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?", updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
//    }
//
//    public void deleteBook(int id){
//        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
//    }
//
//    public void appointBook(int person_id, int book_id){
//        jdbcTemplate.update("INSERT INTO order_book values (?,?)", book_id, person_id);
//    }
}
