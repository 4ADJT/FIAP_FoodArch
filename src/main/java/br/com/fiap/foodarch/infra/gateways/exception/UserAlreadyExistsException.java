package br.com.fiap.foodarch.infra.gateways.exception;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String defaultMessage) {
    super(defaultMessage);
  }
}
