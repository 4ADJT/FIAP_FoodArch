package br.com.fiap.foodarch.application.presenters.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.RestaurantKitchens;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;
import br.com.fiap.foodarch.domain.records.restaurants.kitchen.KitchenDefinitionOutput;
import br.com.fiap.foodarch.domain.records.restaurants.kitchen.RestaurantKitchenOutput;

public class RestaurantKitchenPresenter {
  public static RestaurantKitchenOutput restaurantKitchenResponse(
      RestaurantKitchens restaurantKitchen,
      Restaurant restaurant,
      KitchensDefinition kitchen
  ) {
    return new RestaurantKitchenOutput(
        restaurantKitchen.getId(),
        new RestaurantOutput(
            restaurant.getId(),
            restaurant.getName(),
            restaurant.getOwnerId(),
            restaurant.getCreatedAt()
        ),
        new KitchenDefinitionOutput(
            kitchen.getId(),
            kitchen.getName()
        )
    );
  }
}
