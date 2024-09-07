package br.com.fiap.foodarch.infra.gateways.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends RuntimeException {
  private HttpStatus status;

  public UserAlreadyExistsException(String defaultMessage) {
    super(defaultMessage);
  }

  public UserAlreadyExistsException(String defaultMessage, HttpStatus status) {
    super(defaultMessage);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}