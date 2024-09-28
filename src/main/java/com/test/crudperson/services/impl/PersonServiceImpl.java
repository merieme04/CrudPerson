package com.test.crudperson.services.impl;

import com.test.crudperson.entities.Person;
import com.test.crudperson.config.exception.ResourceNotFoundException;
import com.test.crudperson.repositories.PersonRepository;
import com.test.crudperson.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    // Créer une personne
    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    // Méthode de mise à jour
    @Override
    public Person updatePerson(Long id, Person personDetails) {
        Optional<Person> optionalPerson = getPersonById(id);
        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();
            // Mise à jour des champs
            existingPerson.setNom(personDetails.getNom());
            existingPerson.setPrenom(personDetails.getPrenom());
            existingPerson.setCin(personDetails.getCin());
            existingPerson.setEmail(personDetails.getEmail());
            existingPerson.setVille(personDetails.getVille());
            existingPerson.setTelephone(personDetails.getTelephone());
            existingPerson.setMot_de_passe(personDetails.getMot_de_passe());

            // Enregistrer la personne mise à jour
            return personRepository.save(existingPerson);
        } else {
            throw new ResourceNotFoundException("Person not found with id " + id);
        }
    }

    // Obtenir une personne par ID
    @Override
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    // Supprimer une personne par ID
    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    // Récupérer toutes les personnes
    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Méthode pour récupérer une liste de personnes par nom
    @Override
    public List<Person> getPersonsByNom(String nom) {
        return personRepository.findByNom(nom);
    }

}
