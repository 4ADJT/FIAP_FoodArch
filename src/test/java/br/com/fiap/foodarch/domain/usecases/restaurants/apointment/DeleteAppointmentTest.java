package br.com.fiap.foodarch.domain.usecases.restaurants.appointment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.appointment.RestaurantAppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteAppointmentTest {

  @Mock
  private RestaurantAppointmentRepository repository;

  @InjectMocks
  private DeleteAppointment deleteAppointment;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testExecute() {
    // Arrange
    UUID appointmentId = UUID.randomUUID();

    // Act
    deleteAppointment.execute(appointmentId);

    // Assert
    verify(repository, times(1)).deleteById(appointmentId);
  }
}
