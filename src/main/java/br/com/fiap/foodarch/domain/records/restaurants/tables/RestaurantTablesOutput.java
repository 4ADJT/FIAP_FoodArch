package br.com.fiap.foodarch.domain.records.restaurants.tables;

import java.time.LocalDateTime;
import java.util.UUID;

public record RestaurantTablesOutput(
        UUID RestaurantId,
        UUID Id,
        int tableNumber,
        boolean isAvailable,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
