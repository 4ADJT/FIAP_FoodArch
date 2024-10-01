package br.com.fiap.foodarch.domain.usecases.restaurants.assessment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment.RestaurantAssessmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RestaurantDeleteAssessmentTest {

  @Mock
  private RestaurantAssessmentRepository repository;

  @InjectMocks
  private RestaurantDeleteAssessment restaurantDeleteAssessment;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testExecute() {
    // Arrange
    UUID assessmentId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();

    // Act
    restaurantDeleteAssessment.execute(assessmentId, userId);

    // Assert
    verify(repository, times(1)).deleteRestaurantComment(assessmentId, userId);
  }
}
