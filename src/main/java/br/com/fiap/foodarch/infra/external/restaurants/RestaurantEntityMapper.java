package br.com.fiap.foodarch.infra.external.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.infra.external.users.UserEntity;

public class RestaurantEntityMapper {

  public RestaurantEntity toEntity(Restaurant restaurant, UserEntity userEntity) {
    return new RestaurantEntity(
        restaurant.getName(),
        userEntity
    );
  }

  public Restaurant toDomain(RestaurantEntity entity) {
    return new Restaurant(
        entity.getId(),
        entity.getName(),
        entity.getOwner().getId(),
        entity.getCreated_at(),
        entity.getUpdated_at()
    );
  }
}