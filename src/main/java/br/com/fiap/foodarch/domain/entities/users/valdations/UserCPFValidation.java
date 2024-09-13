package br.com.fiap.foodarch.domain.entities.users.valdations;

import br.com.fiap.foodarch.domain.exceptions.ValidationErrorException;
import org.springframework.http.HttpStatus;

public class UserCPFValidation {
    public static String isValid(String cpf) {
      if(cpf == null) {
        throw new ValidationErrorException("CPF cannot be null.", HttpStatus.BAD_REQUEST);
      }

      if (cpf.isBlank() || cpf.isEmpty()) {
        throw new ValidationErrorException("CPF cannot be empty.", HttpStatus.BAD_REQUEST);
      }

      cpf = cpf.replaceAll("\\D", "").trim();

      if (cpf.length() != 11) {
        throw new ValidationErrorException("CPF should have 11 characters.", HttpStatus.BAD_REQUEST);
      }

      return cpf;
    }

  public static boolean calculatedCPFIsValid(String cpf) {
    // Verifica se o CPF tem 11 dígitos ou se são todos iguais (como 111.111.111-11)
    if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
      return false;
    }

    // Calcula o primeiro dígito verificador
    int sum = 0;
    for (int i = 0; i < 9; i++) {
      sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
    }
    int firstVerifier = 11 - (sum % 11);
    if (firstVerifier >= 10) {
      firstVerifier = 0;
    }

    // Calcula o segundo dígito verificador
    sum = 0;
    for (int i = 0; i < 10; i++) {
      sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
    }
    int secondVerifier = 11 - (sum % 11);
    if (secondVerifier >= 10) {
      secondVerifier = 0;
    }

    boolean isValid = firstVerifier == Character.getNumericValue(cpf.charAt(9)) &&
        secondVerifier == Character.getNumericValue(cpf.charAt(10));

    if(!isValid) {
      throw new ValidationErrorException("CPF is not valid.", HttpStatus.BAD_REQUEST);
    }

    return true;
  }
}
