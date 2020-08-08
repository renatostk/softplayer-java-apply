package br.com.desafio.model;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author renatomori
 */
@Data
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
