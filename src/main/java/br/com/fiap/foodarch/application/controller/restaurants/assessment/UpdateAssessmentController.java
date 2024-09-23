package br.com.fiap.foodarch.application.controller.restaurants.assessment;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAssessmentPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentInput;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.assessment.UpdateRestaurantAssessment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assessment")
@Tag(name = "Assessment")
public class UpdateAssessmentController {
    private final UpdateRestaurantAssessment updateAssessment;

    @Autowired
    public UpdateAssessmentController(
            UpdateRestaurantAssessment updateAssessment
    ) {
        this.updateAssessment = updateAssessment;
    }


    @PutMapping()
    @Operation(summary = "UpdateAssessment", description = "update assessment to FoodArch")
    public ResponseEntity<RestaurantAssessmentOutput> updateAssessment(
            @RequestBody RestaurantAssessmentInput restaurantAssessmentInput
    ) {
        RestaurantAssessment assessment = updateAssessment.execute(restaurantAssessmentInput);

        return ResponseEntity.status(HttpStatus.OK).body(RestaurantAssessmentPresenter.assessmentResponse(assessment));
    }

}
