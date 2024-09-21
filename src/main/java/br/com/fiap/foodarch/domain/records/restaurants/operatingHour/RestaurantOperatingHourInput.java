package br.com.fiap.foodarch.domain.records.restaurants.operatingHour;

import java.time.LocalTime;
import java.util.UUID;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;

public record RestaurantOperatingHourInput(
    UUID restaurantId,
    DayOfWeek dayOfWeek,
    LocalTime openTime,
    LocalTime closeTime) {
}
