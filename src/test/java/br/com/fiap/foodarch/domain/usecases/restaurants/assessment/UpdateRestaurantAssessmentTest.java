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

public class UpdateRestaurantAssessmentTest {

  @Mock
  private RestaurantAssessmentRepository repository;

  @Mock
  private CreateRestaurantAssessmentFactory factory;

  @InjectMocks
  private UpdateRestaurantAssessment updateRestaurantAssessment;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testExecute() {
    // Arrange
    UUID userId = UUID.randomUUID();
    UUID restaurantId = UUID.randomUUID();
    RestaurantAssessmentInput input = new RestaurantAssessmentInput(userId, restaurantId, "Updated comment", true, 4);
    RestaurantAssessment assessment = new RestaurantAssessment(userId, restaurantId, "Updated comment", true, 4);

    when(factory.createAssessment(input.userId(), input.restaurantId(), input.comment(), input.isLike(), input.stars()))
        .thenReturn(assessment);
    when(repository.updateRestaurantComment(assessment)).thenReturn(assessment);

    // Act
    RestaurantAssessment result = updateRestaurantAssessment.execute(input);

    // Assert
    assertEquals(assessment, result);
    verify(factory, times(1)).createAssessment(input.userId(), input.restaurantId(), input.comment(), input.isLike(), input.stars());
    verify(repository, times(1)).updateRestaurantComment(assessment);
  }
}
