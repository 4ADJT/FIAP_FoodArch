package br.com.fiap.foodarch.domain.usecases.restaurants.apointment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.appointment.RestaurantAppointmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.CreateAppointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateAppointmentTest {

  @Mock
  private RestaurantAppointmentRepository repository;

  @InjectMocks
  private CreateAppointment createAppointment;

  private RestaurantAppointment appointment;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);

    // Arrange
    UUID appointmentId = UUID.randomUUID();
    UUID restaurantId = UUID.randomUUID();
    UUID tableId = UUID.randomUUID();
    LocalDateTime reservationDate = LocalDateTime.now().plusDays(1);
    LocalTime startTime = LocalTime.of(19, 0);
    LocalTime endTime = LocalTime.of(21, 0);

    appointment = new RestaurantAppointment();
    appointment.setId(appointmentId);
    appointment.setRestaurantId(restaurantId);
    appointment.setTableId(tableId);
    appointment.setReservationDate(reservationDate);
    appointment.setStartTime(startTime);
    appointment.setEndTime(endTime);
  }

  @Test
  public void testExecute() {
    // Act
    when(repository.createAppointmentRestaurant(appointment)).thenReturn(appointment);
    RestaurantAppointment result = createAppointment.execute(appointment);

    // Assert
    assertEquals(appointment, result);
    verify(repository, times(1)).createAppointmentRestaurant(appointment);
  }

  @Test
  public void testOnCreate() {
    // Act
    appointment.onCreate();

    // Assert
    assertNotNull(appointment.getCreatedAt());
    assertNotNull(appointment.getUpdatedAt());
  }

  @Test
  public void testOnUpdate() {
    // Arrange
    appointment.onCreate();
    LocalDateTime originalCreatedAt = appointment.getCreatedAt();

    // Act
    appointment.onUpdate();

    // Assert
    assertNotNull(appointment.getUpdatedAt());
    assertEquals(originalCreatedAt, appointment.getCreatedAt());
  }
}
