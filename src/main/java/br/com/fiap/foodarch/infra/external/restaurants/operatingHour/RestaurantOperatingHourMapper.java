package br.com.fiap.foodarch.infra.external.restaurants.operatingHour;

import java.time.LocalTime;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;

public class RestaurantOperatingHourMapper {

  // Método para converter do domínio para a entidade de banco de dados
  public RestaurantOperatingHourEntity toEntity(RestaurantOperatingHours operatingHour, RestaurantEntity restaurant) {
    return new RestaurantOperatingHourEntity(
      operatingHour.getDayOfWeek(),
      LocalTime.parse(operatingHour.getOpenTime()),
      LocalTime.parse(operatingHour.getCloseTime()),
      restaurant
    );
  }

  // Método para converter da entidade de banco de dados para o domínio
  public RestaurantOperatingHours toDomain(RestaurantOperatingHourEntity entity) {
    return new RestaurantOperatingHours(
        entity.getId(),
        entity.getRestaurant().getId(),
        entity.getDayOfWeek(),
        entity.getOpenTime().toString(), // Parse openTime de String para LocalTime
        entity.getCloseTime().toString(), // Parse closeTime de String para LocalTime
        entity.getCreatedAt());
  }
}