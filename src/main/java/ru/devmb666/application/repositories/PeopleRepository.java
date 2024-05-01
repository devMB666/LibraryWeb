package ru.devmb666.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.devmb666.application.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>{
}
