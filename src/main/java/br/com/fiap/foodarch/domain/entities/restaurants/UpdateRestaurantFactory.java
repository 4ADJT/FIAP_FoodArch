package br.com.fiap.foodarch.domain.entities.restaurants;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateRestaurantFactory {

      public Restaurants updateRestaurant(UUID id, String name, UUID ownerId, LocalDateTime createdAt) {
        return new Restaurants.UpdateRestaurantBuilder()
            .id(id)
            .name(name)
            .ownerId(ownerId)
            .createdAt(createdAt)
            .build();
      }
}
