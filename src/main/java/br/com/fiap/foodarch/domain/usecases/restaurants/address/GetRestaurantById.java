package br.com.fiap.foodarch.domain.usecases.restaurants.address;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.address.RestaurantAddressRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;

import java.util.UUID;

public class GetRestaurantById {
  private final RestaurantAddressRepository repository;

  public GetRestaurantById(RestaurantAddressRepository repository) {
    this.repository = repository;
  }

  public RestaurantAddresses execute(UUID restaurantId) {
      return repository.findByRestaurantId(restaurantId);
  }
}
