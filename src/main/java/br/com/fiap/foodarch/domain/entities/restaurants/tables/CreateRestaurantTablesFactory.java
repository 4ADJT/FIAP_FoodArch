package br.com.fiap.foodarch.domain.entities.restaurants.tables;

import java.util.UUID;

public class CreateRestaurantTablesFactory {

    public RestaurantTables createRestaurantTable(
            UUID restaurantId,
            int tableNumber,
            boolean available
    ) {
        return RestaurantTables.createRestaurantTable()
                .restaurantId(restaurantId)
                .tableNumber(tableNumber)
                .available(available)
                .build();
    }
}
