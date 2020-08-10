package br.com.desafio.validation;

import br.com.desafio.entity.PersonEntity;
import br.com.desafio.model.Person;
import java.util.Date;
import javax.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author renatomori
 */
class ValidationServiceTest {

    public ValidationServiceTest() {
    }

    private Person validPerson() {
        return new Person("TESTE", "M", "teste@teste.com", new Date(), "", "",
                "552.154.570-00"//CPF valido gerado em https://www.4devs.com.br/gerador_de_cpf
        );
    }

    private Person validPersonButEmail(String email) {
        return new Person("TESTE", "M", email, new Date(), "", "",
                "552.154.570-00"//CPF valido gerado em https://www.4devs.com.br/gerador_de_cpf
        );
    }

    private Person validPersonButCPF(String cpf) {
        return new Person("TESTE", "M", "teste@teste.com", new Date(), "", "", cpf);
    }

    /**
     * Test of validatePerson method, of class ValidationService.
     */
    @Test
    void testValidatePerson_OK() {
        System.out.println("validatePerson_OK");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPerson());
        ValidationService instance = new ValidationService();
        instance.validatePerson(person);

    }

    @Test
    void testValidatePerson_EMAIL_EMPTY() {
        System.out.println("validatePerson_EMAIL_EMPTY");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButEmail(""));
        ValidationService instance = new ValidationService();
        instance.validatePerson(person);

    }

    @Test
    void testValidatePerson_EMAIL_invalid1() {
        System.out.println("validatePerson_EMAIL_INVALID1");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButEmail("email_invalido"));
        ValidationService instance = new ValidationService();

        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("e-mail invalid", ve.getMessage());
        }

    }

    @Test
    void testValidatePerson_EMAIL_invalid2() {
        System.out.println("validatePerson_EMAIL_INVALID2");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButEmail("@email_invalido"));
        ValidationService instance = new ValidationService();

        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("e-mail invalid", ve.getMessage());
        }

    }

    @Test
    void testValidatePerson_EMAIL_invalid3() {
        System.out.println("validatePerson_EMAIL_INVALID3");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButEmail("email_invalido@"));
        ValidationService instance = new ValidationService();

        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("e-mail invalid", ve.getMessage());
        }

    }

    @Test
    void testValidatePerson_EMAIL_invalid4() {
        System.out.println("validatePerson_EMAIL_INVALID4");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButEmail("email@_@invalido"));
        ValidationService instance = new ValidationService();

        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("e-mail invalid", ve.getMessage());
        }

    }

    @Test
    void testValidatePerson_CPF_INVALIDO1() {
        System.out.println("validatePerson_CPF_INVALIDO1");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButCPF(""));
        ValidationService instance = new ValidationService();
        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("CPF invaid", ve.getMessage());
        }

    }

    @Test
    void testValidatePerson_CPF_INVALIDO2() {
        System.out.println("validatePerson_CPF_INVALIDO2");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButCPF("111.111.111-11"));
        ValidationService instance = new ValidationService();
        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("CPF invaid", ve.getMessage());
        }

    }

    @Test
    void testValidatePerson_CPF_INVALIDO3() {
        System.out.println("validatePerson_CPF_INVALIDO2");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButCPF("049.087.590-48"));
        ValidationService instance = new ValidationService();
        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("CPF invaid", ve.getMessage());
        }

    }

    @Test
    void testValidatePerson_CPF_INVALIDO4() {
        System.out.println("validatePerson_CPF_INVALIDO2");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButCPF("049.087.590-58"));
        ValidationService instance = new ValidationService();
        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("CPF invaid", ve.getMessage());
        }

    }

    @Test
    void testValidatePerson_CPF_NOT_FORMATED1() {
        System.out.println("validatePerson_CPF_NOT_FORMATED1");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButCPF("55215457000"));
        ValidationService instance = new ValidationService();
        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("CPF not Formated", ve.getMessage());
        }

    }

    @Test
    void testValidatePerson_CPF_NOT_FORMATED2() {
        System.out.println("validatePerson_CPF_NOT_FORMATED2");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButCPF("552.154.570.00"));
        ValidationService instance = new ValidationService();
        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("CPF not Formated", ve.getMessage());
        }

    }

    @Test
    void testValidatePerson_CPF_NOT_FORMATED3() {
        System.out.println("validatePerson_CPF_NOT_FORMATED3");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButCPF("552-154-570-00"));
        ValidationService instance = new ValidationService();
        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("CPF not Formated", ve.getMessage());
        }

    }

    @Test
    void testValidatePerson_CPF_NOT_FORMATED4() {
        System.out.println("validatePerson_CPF_NOT_FORMATED3");
        PersonEntity person = new PersonEntity();
        person.fromPerson(validPersonButCPF(".552154-570.00"));
        ValidationService instance = new ValidationService();
        try {
            instance.validatePerson(person);
            Assertions.fail("Uma Exception era esperada!");
        } catch (ValidationException ve) {
            Assertions.assertEquals("CPF not Formated", ve.getMessage());
        }

    }
}
