package br.com.fiap.foodarch.domain.records.restaurants.address;

import java.util.UUID;

public record RestaurantAddressInput(
    String street,
    UUID restaurantId,
    String number,
    String neighborhood,
    String city,
    String state,
    String complement,
    String country,
    String zip_code
) {
}
