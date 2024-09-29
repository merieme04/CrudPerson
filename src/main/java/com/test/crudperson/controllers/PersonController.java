package com.test.crudperson.controllers;

import com.test.crudperson.entities.Person;
import com.test.crudperson.config.exception.ResourceNotFoundException;
import com.test.crudperson.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<Person>> getAllPersons(@PageableDefault(size = 7) Pageable pageable) {
        Page<Person> persons = personService.getAllPersons(pageable);
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

    // Récupérer les personnes par nom ou prénom
    @GetMapping("/search")
    public ResponseEntity<Page<Person>> searchPersons(@RequestParam String keyword,
                                                      @PageableDefault(size = 5) Pageable pageable) {
        Page<Person> persons = personService.searchPersons(keyword, pageable);
        return ResponseEntity.ok(persons);
    }

}
