package br.com.fiap.foodarch.domain.records.restaurants.operatingHour;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.DayOfWeek;

public record RestaurantOperatingHourInput(
    UUID restaurantId,
    DayOfWeek dayOfWeek,
    @JsonFormat(pattern = "HH:mm:ss") String openTime,
    @JsonFormat(pattern = "HH:mm:ss") String closeTime
    ) {
}
