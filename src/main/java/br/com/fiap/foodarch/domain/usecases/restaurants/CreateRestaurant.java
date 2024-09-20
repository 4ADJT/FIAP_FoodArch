package br.com.fiap.foodarch.domain.usecases.restaurants;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.CreateRestaurantFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantInput;
import org.springframework.http.HttpStatus;

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

    if(!restaurantInput.ownerId().equals(ownerId)) {
      throw new UserUnauthorizedException("User not authorized", HttpStatus.UNAUTHORIZED);
    }

    Restaurant restaurant = factory.createRestaurant(
        restaurantInput.name(),
        userTo.getId()
    );

    return repository.createRestaurant(restaurant, ownerId);
  }

}
