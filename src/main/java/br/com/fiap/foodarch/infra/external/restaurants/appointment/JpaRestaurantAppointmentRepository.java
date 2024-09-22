package br.com.fiap.foodarch.infra.external.restaurants.appointment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.appointment.RestaurantAppointmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantAppointmentRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaRestaurantAppointmentRepository implements RestaurantAppointmentRepository {
  private final IRestaurantAppointmentRepository repository;
  private final IRestaurantRepository restaurantRepository;
//TODO: add a table  private final ITableRepository tableRepository;
  private final RestaurantAppointmentMapper mapper;

  @Autowired
  public JpaRestaurantAppointmentRepository(
      IRestaurantAppointmentRepository repository,
      IRestaurantRepository restaurantRepository,
      RestaurantAppointmentMapper mapper
  ) {
    this.repository = repository;
    this.restaurantRepository = restaurantRepository;
    this.mapper = mapper;
  }

  @Override
  public RestaurantAppointment createAppointmentRestaurant(RestaurantAppointment restaurantAppointment, UUID restaurantId, UUID tableId) {

    return null;
  }

  @Override
  public RestaurantAppointment updateAppointmentRestaurant(RestaurantAppointment restaurantAppointment, UUID restaurantId, UUID tableId) {
    return null;
  }

  @Override
  public RestaurantAppointment findByAppointmentId(UUID appointmentId) {
    return null;
  }

  @Override
  public void deleteById(UUID id) {

  }
}
