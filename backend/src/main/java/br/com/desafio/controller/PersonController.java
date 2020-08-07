/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.controller;

import br.com.desafio.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author renatomori
 */
@Controller
@RequestMapping(value = "/person")
public class PersonController {

    @GetMapping
    public @ResponseBody
    Person getPerson() {
        Person p = new Person();

        return p;
    }
}
