package br.com.desafio.repository;

import br.com.desafio.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author renatomori
 */
public interface PersonRepository extends CrudRepository<PersonEntity, Integer>{
    
}
