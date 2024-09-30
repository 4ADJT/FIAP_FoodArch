package br.com.fiap.foodarch.application.presenters.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import br.com.fiap.foodarch.domain.records.restaurants.appointment.RestaurantAppointmentOutput;

public class RestaurantAppointmentPresenter {
  public static RestaurantAppointmentOutput restaurantAppointmentResponse(RestaurantAppointment restaurantAppointment) {
    return new RestaurantAppointmentOutput(
          restaurantAppointment.getId(),
          restaurantAppointment.getRestaurantId(),
          restaurantAppointment.getTableId(),
          restaurantAppointment.getReservationDate(),
          restaurantAppointment.getStartTime(),
          restaurantAppointment.getEndTime(),
          restaurantAppointment.getCreatedAt()
      );
  }
}

