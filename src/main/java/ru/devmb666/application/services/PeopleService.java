package ru.devmb666.application.services;

import jakarta.persistence.EntityManager;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.devmb666.application.models.Book;
import ru.devmb666.application.models.Person;
import ru.devmb666.application.repositories.PeopleRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final EntityManager entityManager;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, EntityManager entityManager) {
        this.peopleRepository = peopleRepository;
        this.entityManager = entityManager;
    }

    public List<Book> getAllBooks(int id) {
        Session session = entityManager.unwrap(Session.class);
        Person person = session.get(Person.class, id);
        Hibernate.initialize(person.getBooks());
        //System.out.println(person.getBooks());//+" t: "+ person.getBooks().isEmpty()
        return person.getBooks();
    }

    @Transactional
    public void releaseBook(int book_id){
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, book_id);
        Person person = session.get(Person.class, book.getOwner().getId());
        book.setOwner(null);
        person.getBooks().remove(book);
        //jdbcTemplate.update("DELETE FROM order_book WHERE book_id=?", book_id);
    }

    public List<Person> getAllPeople() {
        return peopleRepository.findAll();
    }

    public Person getPeopleById(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

}
