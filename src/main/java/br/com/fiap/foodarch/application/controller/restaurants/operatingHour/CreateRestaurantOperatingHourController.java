package br.com.fiap.foodarch.application.controller.restaurants.operatingHour;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantOperatingHourPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourInput;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours.CreateRestaurantOperatingHour;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/restaurants/operatingHours")
@Tag(name = "Restaurant - Operating hours")
public class CreateRestaurantOperatingHourController {
  private final CreateRestaurantOperatingHour createRestaurantOperatingHour;

  public CreateRestaurantOperatingHourController(
      CreateRestaurantOperatingHour createRestaurantOperatingHour) {
    this.createRestaurantOperatingHour = createRestaurantOperatingHour;
  }

  @PostMapping("/{restaurantId}")
  @Operation(summary = "Create restaurant operating hour", description = "Create a operating hour for restaurant")
  public ResponseEntity<RestaurantOperatingHourOutput> createRestaurant(
      @PathVariable UUID restaurantId,
      @RequestParam("ownerId") UUID ownerId,
      @RequestBody RestaurantOperatingHourInput restaurantOperatingHourInput
    ) {
    RestaurantOperatingHours restaurantOperatingHour = createRestaurantOperatingHour
        .execute(restaurantId, ownerId, restaurantOperatingHourInput);

    return ResponseEntity.status(201)
        .body(RestaurantOperatingHourPresenter.restaurantOperatingHourResponse(restaurantOperatingHour));
  }
}
