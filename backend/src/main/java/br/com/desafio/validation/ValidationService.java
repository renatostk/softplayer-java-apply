package br.com.desafio.validation;

import br.com.desafio.entity.PersonEntity;
import javax.validation.Valid;
import javax.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author renatomori
 */
@Service
@Validated
public class ValidationService {

    public void validatePerson(@Valid PersonEntity person) {
        if (!isEmailOrEmpty(person.getEmail())) {
            throw new ValidationException("e-mail invalid");
        }
        if (!isCPF(person.getCpf())) {
            throw new ValidationException("CPF invaid");
        }
        if (!isCpfFormated(person.getCpf())) {
            throw new ValidationException("CPF not Formated");
        }

    }

    private static boolean isEmailOrEmpty(String email) {
        return email.isEmpty() //Vazio ou
                || (email.contains("@")//contem caracter @
                && !email.startsWith("@") // e nao começa com '@'
                && !email.endsWith("@")// e nao termina com '@'
                && email.split("@").length == 2);//e tem apenas 1 '@'
    }

    private static boolean isCpfFormated(String cpf) {
        String template = "000.000.000-00";
        if (cpf.length() != template.length()) {
            return false;
        }
        int i = 0;
        for (char c : template.toCharArray()) {
            if ((c == '0' && "1234567890".contains(cpf.substring(i, i + 1)))
                    || (c == '.' && cpf.charAt(i) == '.')
                    || (c == '-' && cpf.charAt(i) == '-')) {
                i++;
            } else {
                return false;
            }
        }
        return true;
    }

//https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
    private static boolean isCPF(String cpf) {

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

        char dig10;
        char dig11;
        int sm;
        int r;
        int peso;

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
        return (dig10 == cpf.charAt(9))
                && (dig11 == cpf.charAt(10));

    }
}
