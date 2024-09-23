package br.com.fiap.foodarch.domain.exceptions.restaurants.assessment;

import org.springframework.http.HttpStatus;

public class AssessmentNotFoundException extends RuntimeException {
  private HttpStatus status;

  public AssessmentNotFoundException(String defaultMessage) {
    super(defaultMessage);
  }

  public AssessmentNotFoundException(String defaultMessage, HttpStatus status) {
    super(defaultMessage);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}