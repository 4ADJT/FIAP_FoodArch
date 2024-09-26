package br.com.fiap.foodarch.domain.entities.restaurants.kitchens;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateRestaurantKitchenFactory {

  public RestaurantKitchens updateRestaurantKitchens(UUID id, UUID restaurantId, UUID kitchenId, LocalDateTime createdAt) {
    return RestaurantKitchens.updateRestaurantKitchens()
        .id(id)
        .restaurantId(restaurantId)
        .kitchenId(kitchenId)
        .createdAt(createdAt)
        .build();
  }
}
