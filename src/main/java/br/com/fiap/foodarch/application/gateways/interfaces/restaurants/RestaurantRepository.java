package br.com.fiap.foodarch.application.gateways.interfaces.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RestaurantRepository {

  Restaurant createRestaurant(Restaurant restaurant);

  Page<Restaurant> listRestaurants(Pageable pageable);

  List<Restaurant> findByOwnerId(UUID ownerId);

  Restaurant updateRestaurant(Restaurant restaurant);

  Restaurant findById(UUID id);

}
