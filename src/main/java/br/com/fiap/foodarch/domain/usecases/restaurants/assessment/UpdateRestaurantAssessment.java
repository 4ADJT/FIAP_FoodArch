package br.com.fiap.foodarch.domain.usecases.restaurants.assessment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment.RestaurantAssessmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.CreateRestaurantAssessmentFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentInput;

public class UpdateRestaurantAssessment {
    private final RestaurantAssessmentRepository repository;
    private final CreateRestaurantAssessmentFactory factory;

    public UpdateRestaurantAssessment(
            RestaurantAssessmentRepository repository,
            CreateRestaurantAssessmentFactory factory
    ){
        this.repository = repository;
        this.factory = factory;
    }

    public RestaurantAssessment execute(RestaurantAssessmentInput restaurantAssessmentInput) {

        RestaurantAssessment assessment = factory.createAssessment(
                restaurantAssessmentInput.userId(),
                restaurantAssessmentInput.restaurantId(),
                restaurantAssessmentInput.comment(),
                restaurantAssessmentInput.isLike(),
                restaurantAssessmentInput.stars()
        );

        return repository.updateRestaurantComment(assessment);
    }

}
