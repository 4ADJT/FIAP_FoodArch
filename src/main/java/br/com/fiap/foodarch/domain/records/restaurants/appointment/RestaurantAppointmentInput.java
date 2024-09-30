package br.com.fiap.foodarch.domain.records.restaurants.appointment;

import java.time.LocalDateTime;
import java.util.UUID;

public record RestaurantAppointmentInput(
    UUID restaurantId,
    UUID tableId,
    String reservationDate,
    LocalDateTime startTime,
    LocalDateTime endTime
) {
}
