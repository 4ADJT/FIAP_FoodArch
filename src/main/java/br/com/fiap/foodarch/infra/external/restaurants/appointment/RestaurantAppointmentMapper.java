package br.com.fiap.foodarch.infra.external.restaurants.appointment;

import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;

public class RestaurantAppointmentMapper {

  public RestaurantAppointmentEntity toEntity(RestaurantAppointmentEntity appointment, RestaurantEntity restaurant) {
    return new RestaurantAppointmentEntity(
      appointment.getReservation_date(),
      appointment.getStart_time(),
      appointment.getEnd_time(),
      restaurant
//    TODO: add a entidade da mesa "table"
    );
  }

  public RestaurantAppointment toDomain(RestaurantAppointmentEntity appointmentEntity,RestaurantEntity restaurantEntity)// TODO: add a table entity
   {
    return new RestaurantAppointment(
            appointmentEntity.getId(),
            restaurantEntity.getId(),
            restaurantEntity.getId(), // TODO: aqui deve ser o id da 'table'
            appointmentEntity.getReservation_date(),
            appointmentEntity.getStart_time(),
            appointmentEntity.getEnd_time(),
            appointmentEntity.getCreated_at(),
            appointmentEntity.getUpdated_at()

    );
  }

}
