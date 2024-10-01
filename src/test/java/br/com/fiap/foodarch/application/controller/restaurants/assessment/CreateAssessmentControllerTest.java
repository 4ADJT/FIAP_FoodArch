package br.com.fiap.foodarch.application.controller.restaurants.assessment;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAssessmentPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentInput;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.assessment.CreateRestaurantAssessment;
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

public class CreateAssessmentControllerTest {

  @Mock
  private CreateRestaurantAssessment createRestaurantAssessment;

  @InjectMocks
  private CreateAssessmentController createAssessmentController;

  private RestaurantAssessmentInput assessmentInput;
  private RestaurantAssessment assessment;
  private RestaurantAssessmentOutput assessmentOutput;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);

    // Mock de input e assessment
    UUID userId = UUID.randomUUID();
    UUID restaurantId = UUID.randomUUID();

    // Usando o input corrigido com validações
    assessmentInput = new RestaurantAssessmentInput(
        userId,
        restaurantId,
        "Great food!",
        true,
        5
    );

    assessment = new RestaurantAssessment();
    assessment.setId(UUID.randomUUID());
    assessment.setUserId(userId);
    assessment.setRestaurantId(restaurantId);
    assessment.setComment("Great food!");
    assessment.setLike(true);
    assessment.setStars(5);
    assessment.setCreatedAt(LocalDateTime.now());

    assessmentOutput = new RestaurantAssessmentOutput(
        assessment.getId(),
        assessment.getUserId(),
        assessment.getRestaurantId(),
        assessment.getComment(),
        assessment.isLike(),
        assessment.getStars(),
        assessment.getCreatedAt()
    );
  }

  @Test
  public void testCreateAssessment() {
    // Arrange
    when(createRestaurantAssessment.execute(assessmentInput)).thenReturn(assessment);

    // Act
    ResponseEntity<RestaurantAssessmentOutput> response = createAssessmentController.createAssessment(assessmentInput);

    // Assert
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(assessmentOutput, RestaurantAssessmentPresenter.assessmentResponse(assessment));
    verify(createRestaurantAssessment, times(1)).execute(assessmentInput);
  }
}
