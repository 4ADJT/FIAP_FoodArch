package br.com.fiap.foodarch.domain.usecases.restaurants;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.UpdateRestaurantFactory;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantInput;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class UpdateRestaurantName {
  private final RestaurantRepository repository;
  private final UpdateRestaurantFactory factory;

  public UpdateRestaurantName(
      RestaurantRepository repository,
      UpdateRestaurantFactory factory
  ) {
    this.repository = repository;
    this.factory = factory;
  }

  public Restaurant execute(RestaurantInput restaurantInput, UUID ownerID, UUID restarantId) {

    Restaurant find = repository.findById(restarantId);

    if (find == null) {
      throw new RestaurantNotFound("Restaurant not found", HttpStatus.NOT_FOUND);
    }

    if(!find.getOwnerId().equals(ownerID)) {
      throw new UserUnauthorizedException("You are not the owner of this restaurant", HttpStatus.UNAUTHORIZED);
    }

    Restaurant restaurant = factory.updateRestaurant(
        find.getId(),
        restaurantInput.name(),
        ownerID,
        find.getCreatedAt()
    );

    return repository.updateRestaurant(restaurant, ownerID);
  }

}
