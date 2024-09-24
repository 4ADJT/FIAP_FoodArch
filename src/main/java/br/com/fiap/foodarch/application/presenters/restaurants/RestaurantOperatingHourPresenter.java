package br.com.fiap.foodarch.application.presenters.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourOutput;

public class RestaurantOperatingHourPresenter {
public static RestaurantOperatingHourOutput restaurantOperatingHourResponse(
    RestaurantOperatingHours restaurantOperatingHours) {
    return new RestaurantOperatingHourOutput(
      restaurantOperatingHours.getId(),
      restaurantOperatingHours.getRestaurantId(),
      restaurantOperatingHours.getDayOfWeek(),
      restaurantOperatingHours.getOpenTime(),
      restaurantOperatingHours.getCloseTime(),
      restaurantOperatingHours.getCreatedAt()
    );
  }
}
