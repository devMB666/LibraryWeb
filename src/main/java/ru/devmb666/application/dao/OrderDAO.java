//package ru.devmb666.application.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class OrderDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public OrderDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<OrderBook> getBookList(int personId){
//        return jdbcTemplate.query("SELECT order_book.person_id, order_book.book_id, b.name, b.author FROM order_book join public.book b on b.id = order_book.book_id WHERE order_book.person_id=?", new OrderMapper(), personId);
//    }
//
//    public Optional<OrderBook> show(int bookId){
//        return jdbcTemplate.query("SELECT * FROM order_book WHERE book_id=?", new BeanPropertyRowMapper<>(OrderBook.class), bookId)
//                .stream().findAny();
//    }
//
//    public void releaseBook(int book_id){
//        jdbcTemplate.update("DELETE FROM order_book WHERE book_id=?", book_id);
//    }
//
//    public Boolean isBookFree(int id){
//        return jdbcTemplate.query("SELECT * FROM order_book WHERE book_id=?", new BeanPropertyRowMapper<>(OrderBook.class), id)
//                .isEmpty();
//    }
//
//    public String getPersonByBookId(int id){
//        try {
//            return jdbcTemplate.queryForObject("SELECT p.name FROM order_book JOIN public.person p on p.id = order_book.person_id WHERE book_id=?", String.class , id);
//        }
//        catch (EmptyResultDataAccessException e){
//            return "null";
//        }
//    }
//}
