package com.debezium.demo.controller;

import com.debezium.demo.request.PersonCreateRequest;
import com.debezium.demo.request.PersonUpdateRequest;
import com.debezium.demo.response.PersonResponse;
import com.debezium.demo.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonResponse> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("{id}")
    public PersonResponse getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping()
    public PersonResponse createPerson(@RequestBody PersonCreateRequest personCreateRequest) {
        return personService.createPerson(personCreateRequest);
    }

    @PutMapping("{id}")
    public PersonResponse updatePerson(@PathVariable Long id, @RequestBody PersonUpdateRequest personUpdateRequest) {
        return personService.updatePerson(id, personUpdateRequest);
    }

    @DeleteMapping("{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }


}