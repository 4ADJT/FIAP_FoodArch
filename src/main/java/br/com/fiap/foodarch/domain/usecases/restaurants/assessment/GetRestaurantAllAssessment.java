package br.com.fiap.foodarch.domain.usecases.restaurants.assessment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment.RestaurantAssessmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetRestaurantAllAssessment {
    private final RestaurantAssessmentRepository repository;

    public GetRestaurantAllAssessment(
            RestaurantAssessmentRepository repository
    ){
        this.repository = repository;
    }

    public Page<RestaurantAssessment> execute(Pageable pageable) {
        return repository.getRestaurantAllAssessments(pageable);
    }

}
