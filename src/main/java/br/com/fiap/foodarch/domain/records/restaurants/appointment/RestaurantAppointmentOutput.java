package br.com.fiap.foodarch.domain.records.restaurants.appointment;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record RestaurantAppointmentOutput(
        UUID id,
        UUID restaurantId,
        UUID tableId,
        LocalDateTime reservationDate,
        LocalTime startTime,
        LocalTime endTime,
        LocalDateTime createdAt
) {
}
