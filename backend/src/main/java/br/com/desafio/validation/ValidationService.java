package br.com.desafio.validation;

import br.com.desafio.entity.PersonEntity;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author renatomori
 */
@Service
@Validated
public class ValidationService {
    
    public void validatePerson(@Valid PersonEntity person){
        System.out.println(String.format("Person %s is valid",person));
    }
    
}
