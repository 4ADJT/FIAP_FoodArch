package br.com.fiap.foodarch.application.gateways.interfaces.restaurants.restaurantTables;

import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;

import java.util.UUID;

public interface RestaurantTablesRepository {

    RestaurantTables createRestaurantTables(UUID restaurantId, RestaurantTables restaurantTables);
}
