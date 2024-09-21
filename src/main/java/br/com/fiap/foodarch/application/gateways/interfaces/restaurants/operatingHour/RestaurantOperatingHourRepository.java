package br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;

import java.util.UUID;

public interface RestaurantOperatingHourRepository {
  RestaurantOperatingHours createRestaurantOperatingHour(RestaurantOperatingHours restaurantOperatingHour, UUID restaurantId);

  RestaurantOperatingHours updateRestaurantOperatingHour(RestaurantOperatingHours restaurantOperatingHour, UUID restaurantId);

  RestaurantOperatingHours findByRestaurantId(UUID restaurantId);

  void deleteById(UUID id);
}
