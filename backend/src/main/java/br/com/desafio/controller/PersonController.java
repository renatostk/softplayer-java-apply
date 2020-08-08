/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.controller;

import br.com.desafio.entity.PersonEntity;
import br.com.desafio.model.Person;
import br.com.desafio.repository.PersonRepository;
import br.com.desafio.validation.ValidationService;
import java.util.Date;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author renatomori
 */
@Validated
//@Controller
@RestController
@RequestMapping(value = "/person"
        //, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
)
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private ValidationService validationService;

    //C
    @PostMapping
    @ResponseBody
    public PersonEntity create(@RequestBody Person p) {
        PersonEntity pe = new PersonEntity();
        pe.fromPerson(p);
        pe.setInsertDate(new Date());
        pe.setUpdateDate(null);
        validationService.validatePerson(pe);
        return repository.save(pe);
    }

    //R
    @GetMapping
    @ResponseBody
    public Iterable<PersonEntity> readAll() {
        return repository.findAll();
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public PersonEntity readById(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    //U
    @PutMapping("/id/{id}")
    @ResponseBody
    public PersonEntity update(
            @PathVariable Integer id,
            @RequestBody Person p) {
        PersonEntity pe = new PersonEntity();
        pe.setId(id);
        pe.fromPerson(p);
        pe.setInsertDate(repository.findById(id).get().getInsertDate());
        pe.setUpdateDate(new Date());
        validationService.validatePerson(pe);
        return repository.save(pe);
    }

    //D
    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
