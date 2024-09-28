package com.test.crudperson.services;

import com.test.crudperson.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person savePerson(Person person);
    Person updatePerson(Long id, Person personDetails);
    Optional<Person> getPersonById(Long id);
    void deletePerson(Long id);
    List<Person> getAllPersons();
    List<Person> getPersonsByNom(String nom);
}
