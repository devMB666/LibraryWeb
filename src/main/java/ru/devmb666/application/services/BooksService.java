package ru.devmb666.application.services;

import jakarta.persistence.EntityManager;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.devmb666.application.models.Book;
import ru.devmb666.application.models.Person;
import ru.devmb666.application.repositories.BookRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BookRepository bookRepository;
    private final EntityManager entityManager;
    private final LocalDate currentDate = LocalDate.now();

    @Autowired
    public BooksService(BookRepository bookRepository, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.entityManager = entityManager;
    }

//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }

    public List<Book> getBooksByNameStartsWith(String bookName) {
        return bookRepository.findBooksByNameStartingWith(bookName);
    }

    public List<Book> getBooks(PageRequest pageRequest) {
        return bookRepository.findAll(pageRequest).getContent();
    }

    public Book getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }

    public boolean isBookFree(int bookId) {
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, bookId);
        return book.getOwner() == null;
    }

    @Transactional
    public void appointBook(int person_id, int book_id){
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, book_id);
        Person person = session.get(Person.class, person_id);
        book.setOwner(person);
        book.setDate(currentDate);
        person.getBooks().add(book);
    }

    @Transactional
    public boolean isExpired(int bookId) {
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, bookId);
        if (book.getDate()==null) return false;
        else {
            Hibernate.initialize(book.getDate());
            return currentDate.getDayOfYear() - book.getDate().getDayOfYear() > 10;
        }
    }

}
