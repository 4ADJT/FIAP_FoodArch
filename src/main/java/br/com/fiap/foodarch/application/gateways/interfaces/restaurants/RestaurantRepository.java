package br.com.fiap.foodarch.application.gateways.interfaces.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.infra.external.users.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RestaurantRepository {

  Restaurant createRestaurant(Restaurant restaurant, UserEntity owner);

  Page<Restaurant> listRestaurants(Pageable pageable);

  Page<Restaurant> findByOwnerId(UUID ownerId, Pageable pageable);

  Restaurant updateRestaurant(Restaurant restaurant, UserEntity owner);

  Restaurant findById(UUID id);

}
