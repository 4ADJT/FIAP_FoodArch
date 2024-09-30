package br.com.fiap.foodarch.domain.exceptions.restaurants;

import org.springframework.http.HttpStatus;

public class RestaurantAppointmentNotFoundException extends RuntimeException {
  private HttpStatus status;
  public RestaurantAppointmentNotFoundException(String defaultMessage, HttpStatus status) {
    super(defaultMessage);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
