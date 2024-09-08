package br.com.fiap.foodarch.domain.entities.user.valdations.exception;

import org.springframework.http.HttpStatus;

public class ValidationErrorException extends IllegalArgumentException {
  private HttpStatus status;

  public ValidationErrorException(String defaultMessage) {
    super(defaultMessage);
  }

  public ValidationErrorException(String defaultMessage, HttpStatus status) {
    super(defaultMessage);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
