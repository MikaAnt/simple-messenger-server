package com.example.backendant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/person")
public class PersonController {
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<Person> createPerson(
            @RequestBody Person personToCreate
    ){
        log.info("Called createPerson");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.createPerson(personToCreate));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Person> getPersonById(
            @PathVariable("id") Long id
    ){
        log.info("Called getPersonById");
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(personService.getPersonById(id));
        } catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePersonById(
            @PathVariable("id") Long id
    ){
        log.info("Called deletePersonById id={}",
                id);
        try {
            personService.deletePersonById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        } catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Person> updatePersonById(
        @PathVariable("id") Long id,
        @RequestBody Person peopleToUpdate
    ){
        log.info("Called peopleStatusToOnline id={}; peopleToUpdate={}",
                id, peopleToUpdate);
        var updated = personService.updatePersonById(id, peopleToUpdate);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updated);

    }

    @PutMapping("/update/status/{id}")
    public ResponseEntity<Person> updateStatusById(
            @PathVariable("id") Long id
    ){
        log.info("Called updateStatusById id={}", id);
        var updated = personService.updateStatusById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updated);
    }

}
