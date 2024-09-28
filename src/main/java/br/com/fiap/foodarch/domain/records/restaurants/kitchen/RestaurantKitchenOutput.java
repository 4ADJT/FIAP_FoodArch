package br.com.fiap.foodarch.domain.records.restaurants.kitchen;

import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;

import java.util.UUID;

public record RestaurantKitchenOutput(
    UUID id,
    RestaurantOutput restaurant,
    KitchenDefinitionOutput kitchen
) {
}
