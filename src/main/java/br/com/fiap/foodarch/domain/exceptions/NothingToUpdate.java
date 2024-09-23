package br.com.fiap.foodarch.domain.exceptions;

import org.springframework.http.HttpStatus;

public class NothingToUpdate extends RuntimeException {
  private HttpStatus status;

  public NothingToUpdate(String defaultMessage) {
    super(defaultMessage);
  }

  public NothingToUpdate(String defaultMessage, HttpStatus status) {
    super(defaultMessage);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
