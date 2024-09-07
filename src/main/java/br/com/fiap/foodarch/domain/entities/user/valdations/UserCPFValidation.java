package br.com.fiap.foodarch.domain.entities.user.valdations;

public class UserCPFValidation {
    public static String isValid(String cpf) {
      if(cpf == null) {
        throw new IllegalArgumentException("CPF cannot be null.");
      }

      if (cpf.isBlank() || cpf.isEmpty()) {
        throw new IllegalArgumentException("CPF cannot be empty.");
      }

      cpf = cpf.replaceAll("\\D", "").trim();

      if (cpf.length() != 11) {
        throw new IllegalArgumentException("CPF should have 11 characters.");
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
      throw new IllegalArgumentException("CPF is not valid.");
    }

    return true;
  }
}
