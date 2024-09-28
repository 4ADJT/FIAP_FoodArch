package br.com.fiap.foodarch.application.presenters.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;
import br.com.fiap.foodarch.domain.records.restaurants.kitchen.KitchenDefinitionOutput;

import java.util.List;

public class RestaurantKitchenDefinitionPresenter {
  public static List<KitchenDefinitionOutput> restaurantKitchenDefinitionResponse(List<KitchensDefinition> kitchensDefinition) {
    return kitchensDefinition.stream().map(
      kitchenDefinition -> new KitchenDefinitionOutput(
        kitchenDefinition.getId(),
        kitchenDefinition.getName()
      )
    ).toList();
  }
}
