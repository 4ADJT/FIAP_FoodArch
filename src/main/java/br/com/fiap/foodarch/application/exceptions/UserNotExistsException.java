package br.com.fiap.foodarch.application.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotExistsException extends RuntimeException {
  private HttpStatus status;

  public UserNotExistsException(String defaultMessage) {
    super(defaultMessage);
  }

  public UserNotExistsException(String defaultMessage, HttpStatus status) {
    super(defaultMessage);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}