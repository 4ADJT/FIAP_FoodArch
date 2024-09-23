package br.com.fiap.foodarch.domain.usecases.restaurants.assessment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment.RestaurantAssessmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;

import java.util.UUID;

public class GetRestaurantAssessment {
    private final RestaurantAssessmentRepository repository;


    public GetRestaurantAssessmentById(
            RestaurantAssessmentRepository repository
    ){
        this.repository = repository;
    }

    public RestaurantAssessment execute(UUID id) {
        return repository.getRestaurantAssessmentById(id);
    }

}
