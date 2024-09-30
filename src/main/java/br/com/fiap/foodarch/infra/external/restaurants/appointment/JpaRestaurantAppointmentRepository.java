package br.com.fiap.foodarch.infra.external.restaurants.appointment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.appointment.RestaurantAppointmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantAppointmentNotFoundException;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import br.com.fiap.foodarch.infra.external.restaurants.tables.RestaurantTablesEntity;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantAppointmentRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantTablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaRestaurantAppointmentRepository implements RestaurantAppointmentRepository {
  private final IRestaurantAppointmentRepository repository;
  private final IRestaurantRepository restaurantRepository;
  private final IRestaurantTablesRepository restaurantTablesRepository;
  private final RestaurantAppointmentMapper mapper;

  @Autowired
  public JpaRestaurantAppointmentRepository(
      IRestaurantAppointmentRepository repository,
      IRestaurantRepository restaurantRepository,
      IRestaurantTablesRepository tablesRepository,
      RestaurantAppointmentMapper mapper
  ) {
    this.repository = repository;
    this.restaurantRepository = restaurantRepository;
    this.restaurantTablesRepository = tablesRepository;
    this.mapper = mapper;
  }

  @Override
  public RestaurantAppointment createAppointmentRestaurant(RestaurantAppointment restaurantAppointment) {
    try {
      RestaurantEntity restaurant = restaurantRepository.findById(restaurantAppointment.getRestaurantId())
              .orElseThrow(() -> new RestaurantNotFound("Restaurant not found.", HttpStatus.BAD_REQUEST));

      RestaurantTablesEntity table = restaurantTablesRepository
              .findByRestaurantAndTableId(restaurantAppointment.getRestaurantId(), restaurantAppointment.getTableId());

      if (table.isAvailable()) {
        restaurantTablesRepository.updateIsAvailableById(table.getId(), false);
        RestaurantAppointmentEntity restaurantAppointmentEntity = mapper.toEntity(restaurantAppointment, restaurant, table);
        RestaurantAppointmentEntity toSave = repository.save(restaurantAppointmentEntity);

        return mapper.toDomain(toSave);
      } else {
        throw new IllegalStateException("A mesa não está disponível no horário determinado.");
      }

    } catch (Exception e) {
      throw new RuntimeException("Não foi possível realizar o registro de appointment.", e);
    }
  }

  @Override
  public RestaurantAppointment updateAppointmentRestaurant(RestaurantAppointment restaurantAppointment) {
    try {
      RestaurantAppointmentEntity existingAppointment = repository.findById(restaurantAppointment.getId())
              .orElseThrow(() -> new RestaurantAppointmentNotFoundException("Appointment not found", HttpStatus.NOT_FOUND));

      RestaurantEntity restaurant = restaurantRepository.findById(restaurantAppointment.getRestaurantId())
              .orElseThrow(() -> new RestaurantNotFound("Restaurant not found.", HttpStatus.BAD_REQUEST));

      RestaurantTablesEntity table = restaurantTablesRepository
              .findByRestaurantAndTableId(restaurantAppointment.getRestaurantId(), restaurantAppointment.getTableId());

      RestaurantAppointmentEntity updatedEntity = mapper.toEntity(restaurantAppointment, restaurant, table);
      updatedEntity.setId(existingAppointment.getId());

      RestaurantAppointmentEntity savedEntity = repository.save(updatedEntity);
      return mapper.toDomain(savedEntity);

    } catch (Exception e) {
      throw new RuntimeException("Não foi possível atualizar o appointment.", e);
    }
  }

  @Override
  public RestaurantAppointment findByAppointmentId(UUID appointmentId) {
    try {
      RestaurantAppointmentEntity appointmentEntity = repository.findById(appointmentId)
              .orElseThrow(() -> new RestaurantAppointmentNotFoundException("Appointment not found", HttpStatus.NOT_FOUND));

      return mapper.toDomain(appointmentEntity);
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar o appointment.", e);
    }
  }

  @Override
  public void deleteById(UUID id) {
    try {
      if (!repository.existsById(id)) {
        throw new RestaurantAppointmentNotFoundException("Appointment not found", HttpStatus.NOT_FOUND);
      }

      repository.deleteById(id);

      RestaurantAppointmentEntity appointment = repository.findByAppointmentId(id);
      restaurantTablesRepository.updateIsAvailableById(appointment.getTable().getId(), true);
    } catch (Exception e) {
      throw new RuntimeException("Não foi possível excluir o appointment.", e);
    }
  }

}
