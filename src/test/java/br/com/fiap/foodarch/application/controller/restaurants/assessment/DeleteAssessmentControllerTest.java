package br.com.fiap.foodarch.application.controller.restaurants.assessment;

import br.com.fiap.foodarch.domain.usecases.restaurants.assessment.RestaurantDeleteAssessment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteAssessmentControllerTest {

  @Mock
  private RestaurantDeleteAssessment restaurantDeleteAssessment;

  @InjectMocks
  private DeleteAssessmentController deleteAssessmentController;

  private UUID idAssessment;
  private UUID idUser;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    idAssessment = UUID.randomUUID();
    idUser = UUID.randomUUID();
  }

  @Test
  public void testDeleteAssessment() {
    // Act
    ResponseEntity<String> response = deleteAssessmentController.deleteAssessment(idAssessment, idUser);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(restaurantDeleteAssessment, times(1)).execute(idAssessment, idUser);
  }
}
