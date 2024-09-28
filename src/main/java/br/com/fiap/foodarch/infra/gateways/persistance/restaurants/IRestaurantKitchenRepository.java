package br.com.fiap.foodarch.infra.gateways.persistance.restaurants;

import br.com.fiap.foodarch.infra.external.restaurants.kitchen.KitchenEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IRestaurantKitchenRepository extends JpaRepository<KitchenEntity, UUID> {

  @Query("""
      SELECT \
      k.restaurant.id as restaurant_id, \
      k.restaurant.name as restaurant_name, \
      k.kitchen.name as kitchen \
      FROM KitchenEntity k \
      WHERE k.kitchen.id is not null \
      AND LOWER(k.kitchen.name) LIKE CONCAT('%', LOWER(:kitchenName), '%')""")
  Page<KitchenEntity> findByKitchenName(@Param("kitchenName") String kitchenName, Pageable pageable);

  @Query("SELECT k from KitchenEntity k WHERE k.restaurant.id = :restaurantId and k.kitchen.id = :kitchenId")
  Optional<KitchenEntity> findByRestaurantIdAndKitchenId(@Param("restaurantId") UUID restaurantId,
                                                         @Param("kitchenId") UUID kitchenId);
}
