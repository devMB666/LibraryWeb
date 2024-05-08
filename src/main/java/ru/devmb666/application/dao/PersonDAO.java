package ru.devmb666.application.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.devmb666.application.models.Book;
import ru.devmb666.application.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Person getPersonByBookId(int book_id){
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, book_id);
        Optional<Person> person = Optional.ofNullable(book.getOwner());
        return person.orElse(null);
    }


//    public List<Person> index(){
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id){
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id) //notice
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Person person){
//        jdbcTemplate.update("INSERT INTO Person(name, date) VALUES (?,?)", person.getName(), person.getDate());
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person SET name=?, date=? WHERE id=?", updatedPerson.getName(), updatedPerson.getDate(), id);
//    }
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
//    }
}
