package br.com.fiap.foodarch.domain.entities.restaurants;

import java.util.UUID;

public class CreateRestaurantFactory {

    public Restaurants createRestaurant(String name, UUID ownerId) {
      return new Restaurants.CreateRestaurantBuilder()
          .name(name)
          .ownerId(ownerId)
          .build();
    }
}
