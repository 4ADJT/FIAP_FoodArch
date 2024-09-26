package br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen;

import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.RestaurantKitchens;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RestaurantKitchenRepository {
  Page<RestaurantKitchens> getAll(Pageable pageable);

  RestaurantKitchens getById(UUID restaurantId);

  RestaurantKitchens save(RestaurantKitchens restaurantKitchens);

  RestaurantKitchens update(RestaurantKitchens restaurantKitchens);

  Page<RestaurantKitchens> searchByKitchenName(String kitchenName, Pageable pageable);
}
