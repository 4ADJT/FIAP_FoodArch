package br.com.fiap.foodarch.domain.entities.restaurants;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateRestaurantFactory {

      public Restaurant updateRestaurant(UUID id, String name, UUID ownerId, LocalDateTime createdAt) {
        return Restaurant.updateRestaurant()
            .id(id)
            .name(name)
            .ownerId(ownerId)
            .createdAt(createdAt)
            .build();
      }
}
