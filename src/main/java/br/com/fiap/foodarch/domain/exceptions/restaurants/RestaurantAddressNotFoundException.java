package br.com.fiap.foodarch.domain.exceptions.restaurants;

import org.springframework.http.HttpStatus;

public class RestaurantAddressNotFoundException extends RuntimeException {
  private HttpStatus status;
  public RestaurantAddressNotFoundException(String defaultMessage, HttpStatus status) {
    super(defaultMessage);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
