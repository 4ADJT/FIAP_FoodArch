package br.com.fiap.foodarch.domain.usecases.restaurants;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.CreateRestaurantFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantInput;

import java.util.UUID;

public class CreateRestaurant {
  private final RestaurantRepository repository;
  private final UserRepository userRepository;
  private final CreateRestaurantFactory factory;

  public CreateRestaurant(
    RestaurantRepository repository,
    UserRepository userRepository,
    CreateRestaurantFactory factory
  ) {
    this.repository = repository;
    this.factory = factory;
    this.userRepository = userRepository;
  }

  public Restaurant execute(RestaurantInput restaurantInput, UUID ownerId) {

    User userTo = this.userRepository.findById(ownerId);

    if(userTo == null) {
      throw new RuntimeException("User not found");
    }

    if(ownerId != userTo.getId()) {
      throw new RuntimeException("User not authorized");
    }

    Restaurant restaurant = factory.createRestaurant(
        restaurantInput.name(),
        userTo.getId()
    );

    return repository.createRestaurant(restaurant, ownerId);
  }

}
