package br.com.fiap.foodarch.application.controller.restaurants.assessment;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAssessmentPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentInput;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.assessment.UpdateRestaurantAssessment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UpdateAssessmentControllerTest {

  @Mock
  private UpdateRestaurantAssessment updateRestaurantAssessment;

  @InjectMocks
  private UpdateAssessmentController updateAssessmentController;

  private RestaurantAssessmentInput assessmentInput;
  private RestaurantAssessment assessment;
  private RestaurantAssessmentOutput assessmentOutput;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);

    // Mocking RestaurantAssessmentInput
    UUID userId = UUID.randomUUID();
    UUID restaurantId = UUID.randomUUID();
    assessmentInput = new RestaurantAssessmentInput(
        userId,
        restaurantId,
        "Updated comment",
        true,
        4
    );

    // Mocking RestaurantAssessment
    assessment = new RestaurantAssessment();
    assessment.setId(UUID.randomUUID());
    assessment.setUserId(userId);
    assessment.setRestaurantId(restaurantId);
    assessment.setComment("Updated comment");
    assessment.setLike(true);
    assessment.setStars(4);
    assessment.setCreatedAt(LocalDateTime.now());

    // Mocking the output transformation
    assessmentOutput = RestaurantAssessmentPresenter.assessmentResponse(assessment);
  }

  @Test
  public void testUpdateAssessmentSuccess() {
    // Arrange
    when(updateRestaurantAssessment.execute(assessmentInput)).thenReturn(assessment);

    // Act
    ResponseEntity<RestaurantAssessmentOutput> response = updateAssessmentController.updateAssessment(assessmentInput);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(assessmentOutput, response.getBody());
    verify(updateRestaurantAssessment, times(1)).execute(assessmentInput);
  }
}
