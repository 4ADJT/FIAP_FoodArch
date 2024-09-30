package br.com.fiap.foodarch.domain.records.restaurants.address;

import java.time.LocalDateTime;
import java.util.UUID;

public record RestaurantAddressOutput(
    UUID id,
    String street,
    UUID restaurantId,
    String number,
    String neighborhood,
    String city,
    String state,
    String complement,
    String country,
    String zip_code,
    LocalDateTime createdAt
) {
}
