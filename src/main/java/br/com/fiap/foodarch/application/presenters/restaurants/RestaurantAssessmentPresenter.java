package br.com.fiap.foodarch.application.presenters.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentOutput;

public class RestaurantAssessmentPresenter {
  public static RestaurantAssessmentOutput assessmentResponse(RestaurantAssessment assessment) {
    return new RestaurantAssessmentOutput(
        assessment.getId(),
        assessment.getUserId(),
        assessment.getRestaurantId(),
        assessment.getComment(),
        assessment.isLike(),
        assessment.getStars(),
        assessment.getCreatedAt()
      );
  }
}
