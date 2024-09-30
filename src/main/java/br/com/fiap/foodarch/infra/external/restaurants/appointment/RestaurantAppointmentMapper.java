package br.com.fiap.foodarch.infra.external.restaurants.appointment;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import br.com.fiap.foodarch.infra.external.restaurants.tables.RestaurantTablesEntity;

public class RestaurantAppointmentMapper {

  public RestaurantAppointmentEntity toEntity(RestaurantAppointment appointment, RestaurantEntity restaurant, RestaurantTablesEntity tables) {
    return new RestaurantAppointmentEntity(
      appointment.getReservationDate(),
      appointment.getStartTime(),
      appointment.getEndTime(),
      restaurant,
      tables
    );
  }

  public RestaurantAppointment toDomain(RestaurantAppointmentEntity appointmentEntity)
   {
    return new RestaurantAppointment(
            appointmentEntity.getId(),
            appointmentEntity.getRestaurant().getId(),
            appointmentEntity.getTable().getId(),
            appointmentEntity.getReservation_date(),
            appointmentEntity.getStart_time(),
            appointmentEntity.getEnd_time(),
            appointmentEntity.getCreated_at(),
            appointmentEntity.getUpdated_at()

    );
  }

}
