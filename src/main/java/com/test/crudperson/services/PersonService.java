package com.test.crudperson.services;

import com.test.crudperson.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person savePerson(Person person);
    Person updatePerson(Long id, Person personDetails);
    Optional<Person> getPersonById(Long id);
    void deletePerson(Long id);
    Page<Person> getAllPersons(Pageable pageable);
    Page<Person> searchPersons(String keyword, Pageable pageable);
}
