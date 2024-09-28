package br.com.fiap.foodarch.domain.records.restaurants.kitchen;

import java.util.UUID;

public record KitchenDefinitionOutput(
    UUID id,
    String name
) {
}
