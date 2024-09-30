package br.com.fiap.foodarch.domain.usecases.restaurants.assessment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment.RestaurantAssessmentRepository;

import java.util.UUID;

public class RestaurantDeleteAssessment {
    private final RestaurantAssessmentRepository repository;

    public RestaurantDeleteAssessment(
            RestaurantAssessmentRepository repository
    ){
        this.repository = repository;
    }

    public void execute(UUID id, UUID userId) {
        repository.deleteRestaurantComment(id, userId);
    }

}
