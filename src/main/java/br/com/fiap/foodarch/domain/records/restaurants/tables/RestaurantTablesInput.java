package br.com.fiap.foodarch.domain.records.restaurants.tables;

import java.util.UUID;

public record RestaurantTablesInput(
        UUID restaurantId,
        int tableNumber,
        boolean is_available
) {
}
