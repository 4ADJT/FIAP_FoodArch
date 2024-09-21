package br.com.fiap.foodarch.domain.records.restaurants;

import java.time.LocalDateTime;
import java.util.UUID;

public record RestaurantOutput(
    UUID id,

    String name,

    UUID ownerId,

    LocalDateTime createdAt
) {
}
