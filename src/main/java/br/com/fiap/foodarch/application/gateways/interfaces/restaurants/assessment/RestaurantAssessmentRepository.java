package br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment;

import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RestaurantAssessmentRepository {

    RestaurantAssessment createRestaurantComment(RestaurantAssessment assessment);

    Page<RestaurantAssessment> getRestaurantAllAssessments(Pageable pageable);

    RestaurantAssessment getRestaurantAssessmentById(UUID id);

    RestaurantAssessment updateRestaurantComment(RestaurantAssessment assessment);

    void deleteRestaurantComment(UUID id, UUID userId);
}
