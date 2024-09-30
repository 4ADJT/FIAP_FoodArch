package br.com.fiap.foodarch.domain.records.restaurants.assessment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record RestaurantAssessmentInput(
    @NotEmpty(message = "user id should not be empty")
    @NotNull(message = "user id should not be null")
    UUID userId,

    @NotNull(message = "Owner id should not be null")
    @NotEmpty(message = "Owner id should not be empty")
    UUID restaurantId,

    @NotNull(message = "Owner id should not be null")
    @NotEmpty(message = "Owner id should not be empty")
    String comment,

    boolean isLike,

    @PositiveOrZero
    int stars
) {
}
