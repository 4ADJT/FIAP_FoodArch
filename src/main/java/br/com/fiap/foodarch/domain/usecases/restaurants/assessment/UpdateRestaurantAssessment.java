package br.com.fiap.foodarch.domain.usecases.restaurants.assessment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment.RestaurantAssessmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentInput;

public class UpdateRestaurantAssessment {
    private final RestaurantAssessmentRepository repository;

    public UpdateRestaurantAssessment(
            RestaurantAssessmentRepository repository
    ){
        this.repository = repository;
    }

    public RestaurantAssessment execute(RestaurantAssessmentInput restaurantAssessmentInput) {

        RestaurantAssessment assessment = new RestaurantAssessment(
                restaurantAssessmentInput.userId(),
                restaurantAssessmentInput.restaurantId(),
                restaurantAssessmentInput.comment(),
                restaurantAssessmentInput.isLike(),
                restaurantAssessmentInput.stars()
        );

        return repository.updateRestaurantComment(assessment);
    }

}
