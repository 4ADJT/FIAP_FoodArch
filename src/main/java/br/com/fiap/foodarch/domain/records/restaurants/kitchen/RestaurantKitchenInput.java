package br.com.fiap.foodarch.domain.records.restaurants.kitchen;

import java.util.UUID;

public record RestaurantKitchenInput(
    UUID restaurantId,
    UUID kitchenId
) {
}
