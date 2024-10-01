package br.com.fiap.foodarch.domain.usecases.restaurants.apointment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.appointment.RestaurantAppointmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.appointment.RestaurantAppointment;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.GetAppointmentById;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetAppointmentByIdTest {

  @Mock
  private RestaurantAppointmentRepository repository;

  @InjectMocks
  private GetAppointmentById getAppointmentById;

  private UUID appointmentId;
  private RestaurantAppointment appointment;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);

    // Arrange
    appointmentId = UUID.randomUUID();
    UUID restaurantId = UUID.randomUUID();
    UUID tableId = UUID.randomUUID();
    appointment = new RestaurantAppointment(appointmentId, restaurantId, tableId, null, null, null, null, null);
  }

  @Test
  public void testExecute() {
    // Arrange
    when(repository.findByAppointmentId(appointmentId)).thenReturn(appointment);

    // Act
    RestaurantAppointment result = getAppointmentById.execute(appointmentId);

    // Assert
    assertEquals(appointment, result);
    verify(repository, times(1)).findByAppointmentId(appointmentId);
  }
}
