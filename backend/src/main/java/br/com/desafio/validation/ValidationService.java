package br.com.desafio.validation;

import br.com.desafio.entity.PersonEntity;
import java.util.InputMismatchException;
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

    public void validatePerson(@Valid PersonEntity person) throws Exception {
        if (!isCPF(person.getCpf())) {
            throw new Exception("CPF invaid");
        }
        System.out.println(String.format("Person %s is valid", person));
    }

//https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
    public static boolean isCPF(String cpf) {
        //remove pontos e traços do cpf. 
        cpf = cpf.replaceAll("\\.", "").replace("-", "");

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        int i = 0;
        while (++i < cpf.length()) {
            if (cpf.charAt(0) != cpf.charAt(i)) {
                break;
            }
        }
        if (i >= 11 || cpf.length() != 11) {
            return false;
        }

        char dig10, dig11;
        int sm, r, peso;

        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                sm += Integer.parseInt(cpf.substring(i, i + 1)) * peso;
                peso--;
            }

            r = 11 - (sm % 11);
            if (r >= 10) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + '0'); // converte no respectivo caractere numerico
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                sm += Integer.parseInt(cpf.substring(i, i + 1)) * peso;
                peso--;
            }

            r = 11 - (sm % 11);
            if (r >= 10) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + '0');
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                return true;
            } else {
                return false;
            }
        } catch (InputMismatchException erro) {
            return false;
        }
    }
}
