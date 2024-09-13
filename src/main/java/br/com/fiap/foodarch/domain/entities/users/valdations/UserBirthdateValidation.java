package br.com.fiap.foodarch.domain.entities.users.valdations;

import br.com.fiap.foodarch.application.exceptions.ValidationErrorException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class UserBirthdateValidation {
  public static boolean isValidBirthdate(LocalDate birthdate) {
    if(birthdate == null) {
      throw new ValidationErrorException("Birthdate cannot be null", HttpStatus.BAD_REQUEST);
    }

    if(birthdate.isAfter(LocalDate.now())) {
      throw new ValidationErrorException("Birthdate cannot be in the future", HttpStatus.BAD_REQUEST);
    }

    return true;
  }


}
