package br.com.fiap.foodarch.domain.usecases.restaurants;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class GetRestaurantsByUser {
  private final RestaurantRepository restaurantRepository;

  public GetRestaurantsByUser(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  public Page<Restaurant> execute(UUID ownerId, Pageable pageable) {
    return restaurantRepository.findByOwnerId(ownerId, pageable);
  }
}
