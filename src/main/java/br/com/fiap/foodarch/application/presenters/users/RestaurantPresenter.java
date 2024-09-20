package br.com.fiap.foodarch.application.presenters.users;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;

public class RestaurantPresenter {
  public static RestaurantOutput restaurantResponse(Restaurant restaurant) {
    return new RestaurantOutput(
        restaurant.getId(),
        restaurant.getName(),
        restaurant.getOwnerId()
      );
  }
}
