package br.com.fiap.foodarch.domain.entities.restaurants.tables;

import java.util.UUID;

public class UpdateRestaurantTableFactory {

    public RestaurantTables updaateRestaurantTables(
            UUID id,
            UUID restaurantId,
            int tableNumber,
            boolean isAvailable
    ) {
        return RestaurantTables.updateRestaurantTable()
                .id(id)
                .restaurantId(restaurantId)
                .tableNumber(tableNumber)
                .available(isAvailable)
                .build();
    }
}
