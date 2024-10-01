package br.com.fiap.foodarch.application.controller.restaurants.assessment;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAssessmentPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.exceptions.restaurants.assessment.AssessmentNotFoundException;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.assessment.GetRestaurantAssessmentById;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetAssessmentControllerTest {

  @Mock
  private GetRestaurantAssessmentById getRestaurantAssessmentById;

  @InjectMocks
  private GetAssessmentController getAssessmentController;

  private UUID assessmentId;
  private RestaurantAssessment assessment;
  private RestaurantAssessmentOutput assessmentOutput;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);

    assessmentId = UUID.randomUUID();

    // Mocking RestaurantAssessment
    assessment = new RestaurantAssessment();
    assessment.setId(assessmentId);
    assessment.setUserId(UUID.randomUUID());
    assessment.setRestaurantId(UUID.randomUUID());
    assessment.setComment("Excellent food!");
    assessment.setLike(true);
    assessment.setStars(5);
    assessment.setCreatedAt(LocalDateTime.now());

    // Mocking the output transformation
    assessmentOutput = RestaurantAssessmentPresenter.assessmentResponse(assessment);
  }

  @Test
  public void testGetAssessmentSuccess() {
    // Arrange
    when(getRestaurantAssessmentById.execute(assessmentId)).thenReturn(assessment);

    // Act
    ResponseEntity<RestaurantAssessmentOutput> response = getAssessmentController.getAssessment(assessmentId);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(assessmentOutput, response.getBody());
    verify(getRestaurantAssessmentById, times(1)).execute(assessmentId);
  }

  @Test
  public void testGetAssessmentNotFound() {
    // Arrange
    when(getRestaurantAssessmentById.execute(assessmentId)).thenThrow(new AssessmentNotFoundException("Não foi encontrado.", HttpStatus.NOT_FOUND));

    // Act & Assert
    AssessmentNotFoundException exception = assertThrows(AssessmentNotFoundException.class, () -> {
      getAssessmentController.getAssessment(assessmentId);
    });

    assertEquals("Não foi encontrado.", exception.getMessage());
    assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    verify(getRestaurantAssessmentById, times(1)).execute(assessmentId);
  }
}
