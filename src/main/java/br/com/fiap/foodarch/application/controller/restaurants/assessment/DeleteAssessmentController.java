package br.com.fiap.foodarch.application.controller.restaurants.assessment;

import br.com.fiap.foodarch.domain.usecases.restaurants.assessment.RestaurantDeleteAssessment;
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
public class DeleteAssessmentController {
    private final RestaurantDeleteAssessment restaurantDeleteAssessment;

    @Autowired
    public DeleteAssessmentController(
            RestaurantDeleteAssessment restaurantDeleteAssessment
    ) {
        this.restaurantDeleteAssessment = restaurantDeleteAssessment;
    }

    @DeleteMapping("/{idAssessment}/{idUser}")
    @Operation(summary = "DeleteAssessment", description = "Delete assessment to FoodArch")
    public ResponseEntity<HttpStatus> createAssessment(
            @PathVariable UUID idAssessment, @PathVariable UUID idUser
    ) {
        restaurantDeleteAssessment.execute(idAssessment, idUser);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpStatus.ACCEPTED);
    }

}
