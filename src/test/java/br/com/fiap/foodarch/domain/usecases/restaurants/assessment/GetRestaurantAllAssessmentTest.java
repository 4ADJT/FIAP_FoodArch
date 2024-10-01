package br.com.fiap.foodarch.domain.usecases.restaurants.assessment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment.RestaurantAssessmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetRestaurantAllAssessmentTest {

  @Mock
  private RestaurantAssessmentRepository repository;

  @InjectMocks
  private GetRestaurantAllAssessment getRestaurantAllAssessment;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testExecute() {
    // Arrange
    Pageable pageable = PageRequest.of(0, 10);
    List<RestaurantAssessment> assessments = Arrays.asList(
        new RestaurantAssessment(), new RestaurantAssessment()
    );
    Page<RestaurantAssessment> assessmentPage = new PageImpl<>(assessments);

    when(repository.getRestaurantAllAssessments(pageable)).thenReturn(assessmentPage);

    // Act
    Page<RestaurantAssessment> result = getRestaurantAllAssessment.execute(pageable);

    // Assert
    assertEquals(assessmentPage, result);
    verify(repository, times(1)).getRestaurantAllAssessments(pageable);
  }
}
