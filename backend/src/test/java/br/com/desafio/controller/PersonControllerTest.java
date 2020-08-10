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
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author renatomori
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    MockMvc mvc;

    @InjectMocks
    PersonController controller;
    @MockBean
//    @Autowired
    PersonRepository repository;
    @MockBean
//    @Autowired
    ValidationService validationService;

    public PersonControllerTest() {
    }

//    @Before
//    void setup() {
//        MockitoAnnotations.initMocks(this);
//        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }
    /**
     * Test of create method, of class PersonController.
     */
    @Test
    void testCreate_OK() throws Exception {
        System.out.println("create");

        Mockito.doNothing().when(validationService)
                .validatePerson(Mockito.isA(PersonEntity.class));
        Mockito.when(repository.save(Mockito.isA(PersonEntity.class)))
                .thenReturn(new PersonEntity());

        mvc.perform(MockMvcRequestBuilders
                .post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(
                        new Person("TESTE", "M", "teste@teste.com", new Date(), "", "", "123.456.789-10"))))
                .andExpect(status().isOk());

        Mockito.verify(validationService).validatePerson(Mockito.isA(PersonEntity.class));
        Mockito.verify(repository).save(Mockito.isA(PersonEntity.class));
        Mockito.verifyNoMoreInteractions(repository);
        Mockito.verifyNoMoreInteractions(validationService);
    }

    @Test
    void testCreate_ConstraintViolationException() throws Exception {
        System.out.println("create");

        Mockito.doThrow(ConstraintViolationException.class)
                .when(validationService)
                .validatePerson(Mockito.isA(PersonEntity.class));

        mvc.perform(MockMvcRequestBuilders
                .post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new Person())))
                .andExpect(status().isBadRequest());

        Mockito.verify(validationService).validatePerson(Mockito.isA(PersonEntity.class));
        Mockito.verifyNoMoreInteractions(repository);
        Mockito.verifyNoMoreInteractions(validationService);
    }

    @Test
    void testCreate_ValidationException() throws Exception {
        System.out.println("create");

        Mockito.doThrow(ValidationException.class)
                .when(validationService)
                .validatePerson(Mockito.isA(PersonEntity.class));

        mvc.perform(MockMvcRequestBuilders
                .post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new Person())))
                .andExpect(status().isBadRequest());

        Mockito.verify(validationService).validatePerson(Mockito.isA(PersonEntity.class));
        Mockito.verifyNoMoreInteractions(repository);
        Mockito.verifyNoMoreInteractions(validationService);
    }

    /**
     * Test of readAll method, of class PersonController.
     */
    @Test
    void testReadAll() throws Exception {
        System.out.println("readAll");

        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        mvc.perform(MockMvcRequestBuilders
                .get("/person"))
                .andExpect(status().isOk());

        Mockito.verify(repository).findAll();
        Mockito.verifyNoMoreInteractions(repository);
        Mockito.verifyNoMoreInteractions(validationService);
    }

    /**
     * Test of readById method, of class PersonController.
     */
    @Test
    void testReadById_OK() throws Exception {
        System.out.println("readById");

        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new PersonEntity()));

        mvc.perform(MockMvcRequestBuilders
                .get("/person/id/{id}", 1))
                .andExpect(status().isOk());

        Mockito.verify(repository).findById(1);
        Mockito.verifyNoMoreInteractions(repository);
        Mockito.verifyNoMoreInteractions(validationService);
    }

    @Test
    void testReadById_BAD_REQUEST() throws Exception {
        System.out.println("readById");

        Mockito.when(repository.findById(1)).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                .get("/person/id/{id}", 1))
                .andExpect(status().isBadRequest());

        Mockito.verify(repository).findById(1);
        Mockito.verifyNoMoreInteractions(repository);
        Mockito.verifyNoMoreInteractions(validationService);
    }

    /**
     * Test of update method, of class PersonController.
     */
    @Test
    void testUpdate_OK() throws Exception {
        System.out.println("update");
        PersonEntity pe = new PersonEntity();
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(pe));
        Mockito.doNothing()
                .when(validationService)
                .validatePerson(Mockito.isA(PersonEntity.class));
        Mockito.when(repository.save(Mockito.isA(PersonEntity.class))).thenReturn(pe);

        mvc.perform(MockMvcRequestBuilders
                .put("/person/id/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new Person())))
                .andExpect(status().isOk());

        Mockito.verify(repository).findById(1);
        Mockito.verify(validationService).validatePerson(Mockito.isA(PersonEntity.class));
        Mockito.verify(repository).save(Mockito.isA(PersonEntity.class));
        Mockito.verifyNoMoreInteractions(repository);
        Mockito.verifyNoMoreInteractions(validationService);

    }

    @Test
    void testUpdate_BAD_REQUEST() throws Exception {
        System.out.println("update");
        PersonEntity pe = new PersonEntity();
        Mockito.when(repository.findById(1)).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                .put("/person/id/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new Person())))
                .andExpect(status().isBadRequest());

        Mockito.verify(repository).findById(1);
        Mockito.verifyNoMoreInteractions(repository);
        Mockito.verifyNoMoreInteractions(validationService);

    }

    /**
     * Test of delete method, of class PersonController.
     */
    @Test
    void testDelete() throws Exception {
        System.out.println("delete");

        Mockito.doNothing().when(repository).deleteById(1);

        mvc.perform(MockMvcRequestBuilders
                .delete("/person/id/{id}", 1))
                .andExpect(status().isOk());

        Mockito.verify(repository).deleteById(1);
        Mockito.verifyNoMoreInteractions(repository);
        Mockito.verifyNoMoreInteractions(validationService);
    }

}
