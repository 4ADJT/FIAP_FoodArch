package br.com.fiap.foodarch.infra.external.restaurants.kitchen;

import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.RestaurantKitchens;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;

public class KitchenMapper {

  public KitchenEntity toEntity(RestaurantEntity restaurantEntity, KitchenDefinitionEntity kitchenDefinitionEntity) {
    KitchenEntity kitchenEntity = new KitchenEntity();
    kitchenEntity.setRestaurant(restaurantEntity);
    kitchenEntity.setKitchen(kitchenDefinitionEntity);
    kitchenEntity.setRestaurant(restaurantEntity);

    return kitchenEntity;
  }

  public RestaurantKitchens toDomain(KitchenEntity entity) {
    return new RestaurantKitchens(
        entity.getId(),
        entity.getRestaurant().getId(),
        entity.getKitchen().getId(),
        entity.getCreated_at(),
        entity.getUpdated_at()
    );
  }

}
