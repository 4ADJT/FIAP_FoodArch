package br.com.fiap.foodarch.domain.entities.restaurants.operatingHour;

import java.util.UUID;

public class CreateRestaurantOperatingHourFactory {

  public RestaurantOperatingHours createRestaurantOperatingHour(UUID restaurantId, DayOfWeek dayOfWeek,
      String openTime,
      String closeTime) {
    return RestaurantOperatingHours.createRestaurantOperatingHour()
        .restaurantId(restaurantId)
        .dayOfWeek(dayOfWeek)
        .openTime(openTime)
        .closeTime(closeTime)
        .build();
  }

}
