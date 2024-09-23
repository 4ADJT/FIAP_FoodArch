package br.com.fiap.foodarch.application.controller.restaurants.assessment;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAssessmentPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.records.restaurants.assessment.RestaurantAssessmentOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.assessment.GetRestaurantAllAssessment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessment")
@Tag(name = "Assessment")
public class GetAllAssessmentController {
    private final GetRestaurantAllAssessment getRestaurantAllAssessment;

    @Autowired
    public GetAllAssessmentController(
            GetRestaurantAllAssessment getRestaurantAllAssessment
    ) {
        this.getRestaurantAllAssessment = getRestaurantAllAssessment;
    }

    @GetMapping
    @Operation(summary = "GetAllAssessment", description = "get all assessment to FoodArch")
    public ResponseEntity<Page<RestaurantAssessmentOutput>> createAssessment(
            @ParameterObject
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        Page<RestaurantAssessment> assessment = getRestaurantAllAssessment.execute(pageable);

        Page<RestaurantAssessmentOutput> output = assessment.map(RestaurantAssessmentPresenter::assessmentResponse);

        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

}
