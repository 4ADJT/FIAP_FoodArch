package br.com.fiap.foodarch.infra.external.restaurants;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public class JpaRestaurantRepository implements RestaurantRepository {
  @Override
  public Restaurant createRestaurant(Restaurant restaurant) {
    return null;
  }

  @Override
  public Page<Restaurant> listRestaurants(Pageable pageable) {
    return null;
  }

  @Override
  public List<Restaurant> findByOwnerId(UUID ownerId) {
    return List.of();
  }

  @Override
  public Restaurant updateRestaurant(Restaurant restaurant) {
    return null;
  }

  @Override
  public Restaurant findById(UUID id) {
    return null;
  }
}
