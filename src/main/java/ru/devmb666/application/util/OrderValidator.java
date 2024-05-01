//package ru.devmb666.application.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//import ru.devmb666.application.dao.OrderDAO;
//import ru.devmb666.application.models.Book;
//
//@Component
//public class OrderValidator implements Validator {
//
//    private final OrderDAO orderDAO;
//
//    @Autowired
//    public OrderValidator(OrderDAO orderDAO) {
//        this.orderDAO = orderDAO;
//    }
//
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Book.class.equals(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        Book book = (Book) target;
//        if (orderDAO.show(book.getId()).isPresent()){
//            errors.rejectValue("id", "", "This book is already taken");
//        }
//    }
//}
