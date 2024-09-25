package br.com.fiap.foodarch.infra.external.restaurants.tables;

import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;

public class RestaurantTablesMapper {

    public RestaurantTablesEntity toEntity(RestaurantTables restaurantTables, RestaurantEntity restaurant) {
        return new RestaurantTablesEntity(
                restaurantTables.getId(),
                restaurant,
                restaurantTables.getTableNumber(),
                restaurantTables.isAvailable()
        );
    }

    public RestaurantTables toDomain(RestaurantTablesEntity restaurantTablesEntity) {
        return new RestaurantTables(
                restaurantTablesEntity.getId(),
                restaurantTablesEntity.getRestaurant().getId(),
                restaurantTablesEntity.getTableNumber(),
                restaurantTablesEntity.isAvailable()
        );
    }
}
