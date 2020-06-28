package com.debezium.demo.service;

import com.debezium.demo.model.Person;
import com.debezium.demo.repository.PersonRepository;
import com.debezium.demo.request.PersonCreateRequest;
import com.debezium.demo.request.PersonUpdateRequest;
import com.debezium.demo.response.PersonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonResponse> getAllPersons() {
        return personRepository.findAll().stream().map(PersonResponse::new).collect(Collectors.toList());
    }

    public PersonResponse getPersonById(Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        return new PersonResponse(Objects.requireNonNull(personOptional.orElse(null)));
    }

    public PersonResponse createPerson(PersonCreateRequest request) {
        Person savedPerson = personRepository.save(request.convertToPerson());
        LOGGER.info("Person successfully saved with firstName: {}, lastName: {}", request.getFirstName(), request.getLastName());
        return new PersonResponse(savedPerson);
    }

    public PersonResponse updatePerson(Long id, PersonUpdateRequest request) {
        Person person = request.convertToPerson();
        person.setId(id);
        Person savedPerson = personRepository.save(person);
        LOGGER.info("Person {} successfully updated with firstName: {}, lastName: {}", id, request.getFirstName(), request.getLastName());
        return new PersonResponse(savedPerson);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
        LOGGER.info("Person {} successfully deleted.", id);
    }
}
