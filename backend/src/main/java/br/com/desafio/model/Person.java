package br.com.desafio.model;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author renatomori
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private String sexo;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private String naturalidade;
    private String nacionalidade;
    private String cpf;
}
