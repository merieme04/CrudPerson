package com.test.crudperson.controllers;

import com.test.crudperson.entities.Person;
import com.test.crudperson.config.exception.ResourceNotFoundException;
import com.test.crudperson.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {

    @Autowired
    private PersonService personService;

    // Créer une nouvelle personne
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person newPerson = personService.savePerson(person);
        return ResponseEntity.ok(newPerson);
    }

    // Récupérer une personne par ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.getPersonById(id);
        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une personne par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer toutes les personnes
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    // Mettre à jour une personne

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        try {
            Person updatedPerson = personService.updatePerson(id, personDetails);
            return ResponseEntity.ok(updatedPerson);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer les personnes by name
    @GetMapping("/by-nom/{nom}")
    public ResponseEntity<List<Person>> getPersonsByNom(@PathVariable String nom) {
        List<Person> persons = personService.getPersonsByNom(nom);
        if (persons.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(persons);
    }
}
