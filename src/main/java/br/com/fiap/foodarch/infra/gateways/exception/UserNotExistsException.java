package br.com.fiap.foodarch.infra.gateways.exception;

public class UserNotExistsException extends RuntimeException {
  public UserNotExistsException(String defaultMessage) {
    super(defaultMessage);
  }
}