package br.com.fiap.foodarch.infra.gateways.persistance.restaurants;

import br.com.fiap.foodarch.infra.external.restaurants.address.RestaurantAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface IRestaurantAddressRepository extends JpaRepository<RestaurantAddressEntity, UUID> {

  @Query("SELECT r FROM RestaurantAddressEntity r WHERE r.restaurant.id = ?1")
  RestaurantAddressEntity findByRestaurantId(UUID restaurantId);

}
