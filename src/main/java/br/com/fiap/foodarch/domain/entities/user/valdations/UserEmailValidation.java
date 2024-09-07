package br.com.fiap.foodarch.domain.entities.user.valdations;

import java.util.regex.Pattern;

public class UserEmailValidation {
  private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  public static boolean isValidEmail(String email) {
    if(email == null || email.isEmpty()) {
      throw new IllegalArgumentException("Email cannot be null or empty");
    }

    if (!pattern.matcher(email).matches()) {
      throw new IllegalArgumentException("Invalid email");
    }

    return true;
  }
}
