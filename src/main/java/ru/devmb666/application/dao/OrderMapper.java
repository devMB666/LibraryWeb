//package ru.devmb666.application.dao;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class OrderMapper implements RowMapper<OrderBook> {
//    @Override
//    public OrderBook mapRow(ResultSet rs, int rowNum) throws SQLException {
//        OrderBook orderBook = new OrderBook();
//
//        orderBook.setPerson_id(rs.getInt("person_id"));
//        orderBook.setBook_id(rs.getInt("book_id"));
//        orderBook.setAuthorName(rs.getString("name"));
//        orderBook.setBookName(rs.getString("author"));
//        return orderBook;
//    }
//}
