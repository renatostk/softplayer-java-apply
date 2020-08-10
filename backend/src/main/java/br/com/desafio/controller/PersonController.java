package br.com.desafio.controller;

import br.com.desafio.model.Person;
import br.com.desafio.entity.PersonEntity;
import br.com.desafio.repository.PersonRepository;
import br.com.desafio.validation.ValidationService;
import java.util.Date;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author renatomori
 */
@Validated
@RestController
@RequestMapping(value = "/person")
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
    public ResponseEntity<PersonEntity> readById(@PathVariable Integer id)   {
        Optional<PersonEntity> opt = repository.findById(id);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //U
    @PutMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity<PersonEntity>  update(
            @PathVariable Integer id,
            @RequestBody Person p)  {

        PersonEntity pe;
        Optional<PersonEntity> opt = repository.findById(id);
        if (opt.isPresent()) {
            pe = opt.get();

            pe.fromPerson(p);
            pe.setUpdateDate(new Date());
            validationService.validatePerson(pe);
            return new ResponseEntity<>(repository.save(pe),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
