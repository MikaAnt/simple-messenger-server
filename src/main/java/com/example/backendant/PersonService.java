package com.example.backendant;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person personToCreate){
        var entityToSave = new PersonEntity(
                null,
                personToCreate.personName(),
                personToCreate.personPassword(),
                PersonStatus.OFFLINE,
                LocalDateTime.now()
        );
        var savedEntity = personRepository.save(entityToSave);
        return toPerson(savedEntity);
    }

    public Person getPersonById(Long id){
        PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found person by id = " + id));
        return toPerson(personEntity);
    }

    public void deletePersonById(Long id){
        if(!personRepository.existsById(id)){
            throw new EntityNotFoundException("Not found person by id = " + id);
        }
        personRepository.deleteById(id);
    }

    public Person updatePersonById(Long id, Person personToUpdate){
        PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found person by id = " + id));
        var personToSave = new PersonEntity(
                personEntity.getId(),
                personToUpdate.personName(),
                personToUpdate.personPassword(),
                personEntity.getPersonStatus(),
                personEntity.getEntryTime()
        );
        var updatedPerson = personRepository.save(personToSave);
        return toPerson(updatedPerson);
    }

    public Person updateStatusById(Long id){
        PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found person by id = " + id));
        var personToUpdate = new PersonEntity(
                personEntity.getId(),
                personEntity.getPersonName(),
                personEntity.getPersonPassword(),
                updateStatus(personEntity.getPersonStatus()),
                LocalDateTime.now()
        );
        var updatedPerson = personRepository.save(personToUpdate);
        return toPerson(updatedPerson);
    }

    private Person toPerson(PersonEntity personEntity){
        return new Person(
                personEntity.getId(),
                personEntity.getPersonName(),
                personEntity.getPersonPassword(),
                personEntity.getPersonStatus(),
                personEntity.getEntryTime()
        );
    }

    private PersonStatus updateStatus(PersonStatus statusNow){
        if(statusNow == PersonStatus.ONLINE){
            return PersonStatus.OFFLINE;
        }
        else{
            return PersonStatus.ONLINE;
        }
    }

}