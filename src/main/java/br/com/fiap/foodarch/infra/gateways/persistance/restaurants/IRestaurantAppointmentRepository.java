package br.com.fiap.foodarch.infra.gateways.persistance.restaurants;

import br.com.fiap.foodarch.infra.external.restaurants.address.RestaurantAddressEntity;
import br.com.fiap.foodarch.infra.external.restaurants.appointment.RestaurantAppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface IRestaurantAppointmentRepository extends JpaRepository<RestaurantAppointmentEntity, UUID> {

  @Query("SELECT r FROM RestaurantAppointmentEntity r WHERE r.id = :appointmentId")
  RestaurantAppointmentEntity findByAppointmentId(UUID appointmentId);
}
