package br.com.fiap.foodarch.domain.records.restaurants.assessment;

import java.time.LocalDateTime;
import java.util.UUID;

public record RestaurantAssessmentOutput(
    UUID id,

    UUID userId,

    UUID restaurantId,

    String comment,

    boolean isLike,

    int stars,

    LocalDateTime createdAt
) {
}
