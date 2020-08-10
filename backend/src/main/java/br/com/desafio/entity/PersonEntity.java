package br.com.desafio.entity;

import br.com.desafio.model.Person;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author renatomori
 */
@Getter
@Setter
@Entity
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //Obs: a data de cadastro e atualização dos dados devem ser armazenados.
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    //Nome - obrigatório
    @NotNull
    @Length(min = 1)
    private String name;

    //Sexo
    @Length(min = 1, max = 1)
    @Pattern(regexp = "M|F")
    private String sexo;

    //E-mail - não obrigatório, deve ser validado caso preenchido
    @NotNull
    private String email;

    //Data de Nascimento - obrigatório, deve ser validada
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date birthday;

    //Naturalidade
    private String naturalidade;

    //Nacionalidade
    private String nacionalidade;

    //CPF - obrigatório, deve ser validado (formato e não pode haver dois cadastros com mesmo cpf)
    @Column(unique = true)
    private String cpf;

    public void fromPerson(Person p) {
        if (p.getName() != null) {
            setName(p.getName());
        }
        if (p.getSexo() != null) {
            setSexo(p.getSexo());
        }
        if (p.getEmail() != null) {
            setEmail(p.getEmail());
        }
        if (p.getBirthday() != null) {
            setBirthday(p.getBirthday());
        }
        if (p.getNaturalidade() != null) {
            setNaturalidade(p.getNaturalidade());
        }
        if (p.getNacionalidade() != null) {
            setNacionalidade(p.getNacionalidade());
        }
        if (p.getCpf() != null) {
            setCpf(p.getCpf());
        }
    }

}
