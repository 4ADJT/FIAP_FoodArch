package br.com.fiap.foodarch.domain.entities.restaurants.kitchens;

import java.util.UUID;

public class CreateRestaurantKitchenFactory {

  public RestaurantKitchens createRestaurantKitchens(UUID restaurantId, UUID kitchenId) {
    return RestaurantKitchens.createRestaurantKitchens()
        .restaurantId(restaurantId)
        .kitchenId(kitchenId)
        .build();
  }
}
