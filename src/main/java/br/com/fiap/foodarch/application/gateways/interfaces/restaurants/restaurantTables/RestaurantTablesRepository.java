package br.com.fiap.foodarch.application.gateways.interfaces.restaurants.restaurantTables;

import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;

import java.util.UUID;

public interface RestaurantTablesRepository {

    RestaurantTables createRestaurantTables(UUID restaurantId, RestaurantTables restaurantTables);

    RestaurantTables findByRestaurantTableNumber(int tableNumber);

    RestaurantTables findByRestaurantId(UUID restaurantTablesId, UUID restaurantId);

    void deleteById(UUID id);

    RestaurantTables updateRestaurantTables(RestaurantTables restaurantTables, UUID restaurantId);
}
