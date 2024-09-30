package br.com.fiap.foodarch.infra.gateways.persistance.restaurants;

import br.com.fiap.foodarch.infra.external.restaurants.tables.RestaurantTablesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IRestaurantTablesRepository extends JpaRepository<RestaurantTablesEntity, UUID> {

    @Query("SELECT r FROM RestaurantTablesEntity r WHERE r.restaurant.id = :restaurantId")
    List<RestaurantTablesEntity> findByRestaurantId(UUID restaurantId);

    @Query("SELECT t from RestaurantTablesEntity t where t.restaurant.id = :restaurantId and t.id = :tableId")
    RestaurantTablesEntity findByRestaurantAndTableId(UUID restaurantId, UUID tableId);

    RestaurantTablesEntity findByTableNumber(int tableNumber);
}
