/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.controller;

import br.com.desafio.model.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author renatomori
 */
@Controller
@RequestMapping(value = "/person")
public class PersonController {

    //C
    @PostMapping
    @ResponseBody
    public Person create(Person p) {
        return p;
    }

    //R
    @GetMapping
    @ResponseBody
    public List<Person> readAll() {
        Person p = new Person();
        List<Person> persons = new ArrayList<>();
        persons.add(p);
        return persons;
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public Person readById(@PathVariable int id) {
        Person p = new Person();
        p.setId(id);
        return p;
    }

    //U
    @PutMapping
    @ResponseBody
    public Person update(Person p) {
        return p;
    }

    //D
    @DeleteMapping
    @ResponseBody
    public Person delete() {
        return new Person();
    }
}
