package br.com.fiap.foodarch.application.controller.restaurants.assessment;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAssessmentPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.assessment.GetRestaurantAllAssessment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetAllAssessmentControllerTest {

  @Mock
  private GetRestaurantAllAssessment getRestaurantAllAssessment;

  @InjectMocks
  private GetAllAssessmentController getAllAssessmentController;

  private Pageable pageable;
  private Page<RestaurantAssessment> assessmentPage;
  private Page<RestaurantAssessmentOutput> assessmentOutputPage;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);

    pageable = PageRequest.of(0, 10);

    // Mocking RestaurantAssessment
    RestaurantAssessment assessment = new RestaurantAssessment();
    assessment.setId(UUID.randomUUID());
    assessment.setUserId(UUID.randomUUID());
    assessment.setRestaurantId(UUID.randomUUID());
    assessment.setComment("Great food!");
    assessment.setLike(true);
    assessment.setStars(5);
    assessment.setCreatedAt(LocalDateTime.now());

    // Creating a page of assessments
    assessmentPage = new PageImpl<>(List.of(assessment), pageable, 1);

    // Mocking the output transformation
    RestaurantAssessmentOutput assessmentOutput = RestaurantAssessmentPresenter.assessmentResponse(assessment);
    assessmentOutputPage = new PageImpl<>(List.of(assessmentOutput), pageable, 1);
  }

  @Test
  public void testGetAllAssessment() {
    // Arrange
    when(getRestaurantAllAssessment.execute(pageable)).thenReturn(assessmentPage);

    // Act
    ResponseEntity<Page<RestaurantAssessmentOutput>> response = getAllAssessmentController.getAllAssessment(pageable);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(assessmentOutputPage, response.getBody());
    verify(getRestaurantAllAssessment, times(1)).execute(pageable);
  }
}
