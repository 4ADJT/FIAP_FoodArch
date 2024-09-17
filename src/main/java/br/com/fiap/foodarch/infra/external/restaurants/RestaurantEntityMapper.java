package br.com.fiap.foodarch.infra.external.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.infra.external.users.UserEntity;

public class RestaurantEntityMapper {

  public RestaurantEntity toEntity(Restaurant restaurant, UserEntity owner) {
    if (restaurant == null || owner == null) {
      return null;
    }

    RestaurantEntity restaurantEntity = new RestaurantEntity();
    restaurantEntity.setId(restaurant.getId());
    restaurantEntity.setName(restaurant.getName());
    restaurantEntity.setOwner(owner);
    restaurantEntity.setCreated_at(restaurant.getCreatedAt());
    restaurantEntity.setUpdated_at(restaurant.getUpdatedAt());

    return restaurantEntity;
  }

  public Restaurant toDomain(RestaurantEntity restaurantEntity) {
    if (restaurantEntity == null) {
      return null;
    }

    Restaurant restaurant = new Restaurant();
    restaurant.setId(restaurantEntity.getId());
    restaurant.setName(restaurantEntity.getName());
    restaurant.setOwnerId(restaurantEntity.getOwner().getId());
    restaurant.setCreatedAt(restaurantEntity.getCreated_at());
    restaurant.setUpdatedAt(restaurantEntity.getUpdated_at());

    return restaurant;
  }
}