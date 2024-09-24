package br.com.fiap.foodarch.infra.external.restaurants.tables;

import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;

public class RestaurantTablesMapper {

    public RestaurantTablesEntity toEntity(RestaurantTables restaurantTables) {
        return new RestaurantTablesEntity(
                restaurantTables.getId(),
                restaurantTables.getRestaurantId(),
                restaurantTables.getTableNumber(),
                restaurantTables.isAvailable()
        );
    }

    public RestaurantTables toDomain(RestaurantTablesEntity restaurantTablesEntity) {
        return new RestaurantTables(
                restaurantTablesEntity.getId(),
                restaurantTablesEntity.getRestaurantId(),
                restaurantTablesEntity.getTableNumber(),
                restaurantTablesEntity.isAvailable()
        );
    }
}
