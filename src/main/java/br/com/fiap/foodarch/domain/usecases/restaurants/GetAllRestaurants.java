package br.com.fiap.foodarch.domain.usecases.restaurants;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetAllRestaurants {
  private final RestaurantRepository repository;

  public GetAllRestaurants(RestaurantRepository repository) {
    this.repository = repository;
  }

  public Page<Restaurant> execute(Pageable pageable) {
    return repository.listRestaurants(pageable);
  }

}
