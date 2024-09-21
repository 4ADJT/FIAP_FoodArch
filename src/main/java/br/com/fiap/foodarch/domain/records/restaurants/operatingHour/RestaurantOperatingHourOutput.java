package br.com.fiap.foodarch.domain.records.restaurants.operatingHour;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;

public record RestaurantOperatingHourOutput(
    UUID id,
    UUID restaurantId,
    DayOfWeek dayOfWeek,
    LocalTime openTime,
    LocalTime closeTime,
    LocalDateTime createdAt) {
}
