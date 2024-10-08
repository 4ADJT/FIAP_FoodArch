package br.com.fiap.foodarch.domain.records.restaurants;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RestaurantInput(
    @NotEmpty(message = "Name should not be empty")
    @NotNull(message = "Name should not be null")
    String name,

    @NotNull(message = "Owner id should not be null")
    @NotEmpty(message = "Owner id should not be empty")
    UUID ownerId
) {
}
