package br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;

import java.util.List;
import java.util.UUID;

public interface RestaurantOperatingHourRepository {
  RestaurantOperatingHours createRestaurantOperatingHour(RestaurantOperatingHours restaurantOperatingHour, UUID restaurantId);

  RestaurantOperatingHours updateRestaurantOperatingHour(RestaurantOperatingHours restaurantOperatingHour, UUID restaurantId);

  List<RestaurantOperatingHours> findByRestaurantId(UUID restaurantId);

  RestaurantOperatingHours findByRestaurantIdAndDayOfWeek(UUID restaurantId, DayOfWeek dayOfWeek);

  void deleteById(UUID id);
}
