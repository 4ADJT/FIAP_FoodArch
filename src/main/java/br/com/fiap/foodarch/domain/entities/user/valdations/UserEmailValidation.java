package br.com.fiap.foodarch.domain.entities.user.valdations;

import br.com.fiap.foodarch.domain.entities.user.valdations.exception.ValidationErrorException;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

public class UserEmailValidation {
  private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  public static boolean isValidEmail(String email) {
    if(email == null || email.isEmpty()) {
      throw new ValidationErrorException("Email cannot be null or empty",HttpStatus.BAD_REQUEST);
    }

    if (!pattern.matcher(email).matches()) {
      throw new ValidationErrorException("Invalid email", HttpStatus.BAD_REQUEST);
    }

    return true;
  }
}
