package br.com.fiap.foodarch.domain.usecases.restaurants.address;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.address.RestaurantAddressRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantAddressNotFoundException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class GetRestaurantById {
  private final RestaurantAddressRepository repository;

  public GetRestaurantById(RestaurantAddressRepository repository) {
    this.repository = repository;
  }

  public RestaurantAddresses execute(UUID restaurantId) {
    if(restaurantId == null) {
      throw new IllegalArgumentException("Restaurant id is required");
    }

    if(repository.findByRestaurantId(restaurantId) == null) {
      throw new RestaurantAddressNotFoundException("Restaurant address not found", HttpStatus.NOT_FOUND);
    }

    return repository.findByRestaurantId(restaurantId);
  }
}
