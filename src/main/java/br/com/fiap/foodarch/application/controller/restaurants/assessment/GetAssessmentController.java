package br.com.fiap.foodarch.application.controller.restaurants.assessment;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAssessmentPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.assessment.GetRestaurantAssessmentById;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/assessment")
@Tag(name = "Assessment")
public class GetAssessmentController {
    private final GetRestaurantAssessmentById getRestaurantAssessmentById;

    @Autowired
    public GetAssessmentController(
            GetRestaurantAssessmentById getRestaurantAssessmentById
    ) {
        this.getRestaurantAssessmentById = getRestaurantAssessmentById;
    }

    @GetMapping("/{id}")
    @Operation(summary = "GetAssessment", description = "get assessment by id to FoodArch")
    public ResponseEntity<RestaurantAssessmentOutput> getAssessment(
            @PathVariable UUID id
    ) {
        RestaurantAssessment assessment = getRestaurantAssessmentById.execute(id);

        return ResponseEntity.status(HttpStatus.OK).body(RestaurantAssessmentPresenter.assessmentResponse(assessment));
    }

}
