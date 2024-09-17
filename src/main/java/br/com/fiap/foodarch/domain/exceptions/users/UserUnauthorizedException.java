package br.com.fiap.foodarch.domain.exceptions.users;

import org.springframework.http.HttpStatus;

public class UserUnauthorizedException extends RuntimeException {
  private HttpStatus status;

  public UserUnauthorizedException(String defaultMessage) {
    super(defaultMessage);
  }

  public UserUnauthorizedException(String defaultMessage, HttpStatus status) {

    super(defaultMessage);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
