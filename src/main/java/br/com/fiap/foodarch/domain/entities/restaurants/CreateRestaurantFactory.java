package br.com.fiap.foodarch.domain.entities.restaurants;

import java.util.UUID;

public class CreateRestaurantFactory {

    public Restaurant createRestaurant(String name, UUID ownerId) {
      return new Restaurant.CreateRestaurantBuilder()
          .name(name)
          .ownerId(ownerId)
          .build();
    }
}
