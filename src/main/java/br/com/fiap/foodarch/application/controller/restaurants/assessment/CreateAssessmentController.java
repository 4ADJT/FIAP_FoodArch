package br.com.fiap.foodarch.application.controller.restaurants.assessment;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAssessmentPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentInput;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.assessment.CreateRestaurantAssessment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessment")
@Tag(name = "Assessment")
public class CreateAssessmentController {
    private final CreateRestaurantAssessment createRestaurantAssessment;

    @Autowired
    public CreateAssessmentController(
            CreateRestaurantAssessment createRestaurantAssessment
    ) {
        this.createRestaurantAssessment = createRestaurantAssessment;
    }


    @PostMapping()
    @Operation(summary = "CreateAssessment", description = "create new assessment to FoodArch")
    public ResponseEntity<RestaurantAssessmentOutput> createAssessment(
            @RequestBody RestaurantAssessmentInput restaurantAssessmentInput
    ) {

        RestaurantAssessment assessment = createRestaurantAssessment.execute(restaurantAssessmentInput);

        return ResponseEntity.status(HttpStatus.CREATED).body(RestaurantAssessmentPresenter.assessmentResponse(assessment));
    }

}
