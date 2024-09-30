package br.com.fiap.foodarch.application.controller.restaurants.operatingHour;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantOperatingHourPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourInput;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours.UpdateRestaurantOperatingHour;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants/operatingHours")
@Tag(name = "Restaurant - Operating hours")
public class UpdateRestaurantOperatingHourController {
    private final UpdateRestaurantOperatingHour updateRestaurantOperatingHour;

    public UpdateRestaurantOperatingHourController(UpdateRestaurantOperatingHour updateRestaurantOperatingHour) {
        this.updateRestaurantOperatingHour = updateRestaurantOperatingHour;
    }

    @PutMapping("/{restaurantId}")
    @Operation(summary = "Update restaurant operating hour", description = "Update a operating hour for restaurant")
    public ResponseEntity<RestaurantOperatingHourOutput> UpdateRestaurantOperatingHour(
            @PathVariable UUID restaurantId,
            @RequestParam("ownerId") UUID ownerId,
            @RequestBody RestaurantOperatingHourInput restaurantOperatingHourInput
    ) {
        RestaurantOperatingHours restaurantOperatingHour = updateRestaurantOperatingHour.execute(restaurantId, ownerId, restaurantOperatingHourInput);

        return ResponseEntity.status(201)
                .body(RestaurantOperatingHourPresenter.restaurantOperatingHourResponse(restaurantOperatingHour));
    }
}