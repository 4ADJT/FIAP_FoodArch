package br.com.fiap.foodarch.application.presenters.users;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;
import br.com.fiap.foodarch.domain.records.users.UserOutput;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserPresenter {
  public static UserOutput userResponse(User user) {
    Set<Restaurant> restaurants = new HashSet<>(user.getRestaurants());

    Set<RestaurantOutput> restaurantsOutput = restaurants.stream()
      .map(restaurant -> new RestaurantOutput(
        restaurant.getId(),
        restaurant.getName(),
        restaurant.getOwnerId()
      ))
      .collect(Collectors.toSet());

    return new UserOutput(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getBirthdate(),
        user.getCpf(),
        restaurantsOutput
      );
  }

}
