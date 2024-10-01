package br.com.fiap.foodarch.domain.usecases.restaurants.assessment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment.RestaurantAssessmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetRestaurantAssessmentByIdTest {

  @Mock
  private RestaurantAssessmentRepository repository;

  @InjectMocks
  private GetRestaurantAssessmentById getRestaurantAssessmentById;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testExecute() {
    // Arrange
    UUID id = UUID.randomUUID();
    RestaurantAssessment assessment = new RestaurantAssessment(id, UUID.randomUUID(), "Good food", true, 5);

    when(repository.getRestaurantAssessmentById(id)).thenReturn(assessment);

    // Act
    RestaurantAssessment result = getRestaurantAssessmentById.execute(id);

    // Assert
    assertEquals(assessment, result);
    verify(repository, times(1)).getRestaurantAssessmentById(id);
  }
}
