package br.com.fiap.foodarch.application.gateways.interfaces.restaurants.appointment;

import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;

import java.util.UUID;

public interface RestaurantAppointmentRepository {

  RestaurantAppointment createAppointmentRestaurant(RestaurantAppointment restaurantAppointment, UUID restaurantId, UUID tableId);

  RestaurantAppointment updateAppointmentRestaurant(RestaurantAppointment restaurantAppointment, UUID restaurantId, UUID tableId);

  RestaurantAppointment findByAppointmentId(UUID appointmentId);

  void deleteById(UUID id);
}
