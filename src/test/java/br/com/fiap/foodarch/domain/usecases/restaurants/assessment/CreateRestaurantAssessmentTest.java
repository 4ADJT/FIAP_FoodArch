package br.com.fiap.foodarch.domain.usecases.restaurants.assessment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment.RestaurantAssessmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.CreateRestaurantAssessmentFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CreateRestaurantAssessmentTest {

  @Mock
  private RestaurantAssessmentRepository repository;

  @Mock
  private CreateRestaurantAssessmentFactory factory;

  @InjectMocks
  private CreateRestaurantAssessment createRestaurantAssessment;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testExecute() {
    // Arrange
    UUID userId = UUID.randomUUID();
    UUID restaurantId = UUID.randomUUID();
    RestaurantAssessmentInput input = new RestaurantAssessmentInput(userId, restaurantId, "Good food", true, 5);
    RestaurantAssessment assessment = new RestaurantAssessment(userId, restaurantId, "Good food", true, 5);

    when(factory.createAssessment(input.userId(), input.restaurantId(), input.comment(), input.isLike(), input.stars()))
        .thenReturn(assessment);
    when(repository.createRestaurantComment(assessment)).thenReturn(assessment);

    // Act
    RestaurantAssessment result = createRestaurantAssessment.execute(input);

    // Assert
    assertEquals(assessment, result);
    verify(factory, times(1)).createAssessment(input.userId(), input.restaurantId(), input.comment(), input.isLike(), input.stars());
    verify(repository, times(1)).createRestaurantComment(assessment);
  }
}
