package br.com.fiap.foodarch.infra.gateways.persistance.restaurants;


import br.com.fiap.foodarch.infra.external.restaurants.operatingHour.RestaurantOperatingHourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;


public interface IRestaurantOperatingHourRepository extends JpaRepository<RestaurantOperatingHourEntity, UUID> {

  @Query("SELECT r FROM RestaurantOperatingHourEntity r WHERE r.restaurant.id = ?1")
  RestaurantOperatingHourEntity findByRestaurantId(UUID restaurantId);


}
