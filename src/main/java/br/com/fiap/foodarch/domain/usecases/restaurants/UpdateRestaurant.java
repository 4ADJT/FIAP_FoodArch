package br.com.fiap.foodarch.domain.usecases.restaurants;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.UpdateRestaurantFactory;

import java.util.UUID;

public class UpdateRestaurant {
  private final RestaurantRepository repository;
  private final UpdateRestaurantFactory factory;

  public UpdateRestaurant(
      RestaurantRepository repository,
      UpdateRestaurantFactory factory
  ) {
    this.repository = repository;
    this.factory = factory;
  }

  public Restaurant execute(Restaurant restaurant, UUID ownerID) {
    return repository.updateRestaurant(restaurant, ownerID);
  }

}
