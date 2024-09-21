package br.com.fiap.foodarch.infra.external.restaurants.operatingHour;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;

public class RestaurantOperatingHourMapper {
  public RestaurantOperatingHourEntity toEntity(RestaurantOperatingHours operatingHour, RestaurantEntity restaurant) {
    return new RestaurantOperatingHourEntity(
        operatingHour.getDayOfWeek(),
        operatingHour.getOpenTime(),
        operatingHour.getCloseTime(),
        restaurant);
  }

  public RestaurantOperatingHours toDomain(RestaurantOperatingHourEntity entity) {
    return new RestaurantOperatingHours(
        entity.getId(), 
        entity.getRestaurant().getId(),
        entity.getDayOfWeek(),
        entity.getOpenTime(),
        entity.getCloseTime(),
        entity.getCreatedAt()
    );
  }
}
