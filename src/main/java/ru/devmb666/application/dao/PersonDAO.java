package ru.devmb666.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.devmb666.application.models.Book;
import ru.devmb666.application.models.OrderBook;
import ru.devmb666.application.models.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id) //notice
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(name, date) VALUES (?,?)", person.getName(), person.getDate());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, date=? WHERE id=?", updatedPerson.getName(), updatedPerson.getDate(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }

    public List<OrderBook> getBookList(int personId){
        return jdbcTemplate.query("SELECT order_book.person_id, order_book.book_id, b.name, b.author FROM order_book join public.book b on b.id = order_book.book_id WHERE order_book.person_id=?", new OrderMapper(), personId);
    }

    public void releaseBook(int book_id){
        jdbcTemplate.update("DELETE FROM order_book WHERE book_id=?", book_id);
    }
}
