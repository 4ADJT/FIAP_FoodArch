package br.com.fiap.foodarch.infra.gateways.persistance.restaurants;


import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;
import br.com.fiap.foodarch.infra.external.restaurants.operatingHour.RestaurantOperatingHourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface IRestaurantOperatingHourRepository extends JpaRepository<RestaurantOperatingHourEntity, UUID> {

  @Query("SELECT r FROM RestaurantOperatingHourEntity r WHERE r.restaurant.id = ?1")
  List<RestaurantOperatingHourEntity> findByRestaurantId(UUID restaurantId);

  @Query("SELECT ro FROM RestaurantOperatingHourEntity ro WHERE ro.restaurant.id = :restaurantId AND ro.dayOfWeek = :dayOfWeek")
  RestaurantOperatingHourEntity findByRestaurantIdAndDayOfWeek(@Param("restaurantId") UUID restaurantId, @Param("dayOfWeek") DayOfWeek dayOfWeek);
}
