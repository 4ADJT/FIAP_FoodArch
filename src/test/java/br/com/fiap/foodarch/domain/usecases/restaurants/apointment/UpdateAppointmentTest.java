package br.com.fiap.foodarch.domain.usecases.restaurants.apointment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.appointment.RestaurantAppointmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.UpdateAppointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UpdateAppointmentTest {

  @Mock
  private RestaurantAppointmentRepository repository;

  @InjectMocks
  private UpdateAppointment updateAppointment;

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
    // Arrange
    when(repository.updateAppointmentRestaurant(appointment)).thenReturn(appointment);

    // Act
    RestaurantAppointment result = updateAppointment.execute(appointment);

    // Assert
    assertEquals(appointment, result);
    verify(repository, times(1)).updateAppointmentRestaurant(appointment);
  }
}
