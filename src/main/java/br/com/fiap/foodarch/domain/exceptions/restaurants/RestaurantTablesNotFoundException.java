package br.com.fiap.foodarch.domain.exceptions.restaurants;

import org.springframework.http.HttpStatus;

public class RestaurantTablesNotFoundException extends RuntimeException {

    private HttpStatus status;

    public RestaurantTablesNotFoundException(String defaultMessage, HttpStatus httpStatus) {
        super(defaultMessage);
        this.status = httpStatus;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
