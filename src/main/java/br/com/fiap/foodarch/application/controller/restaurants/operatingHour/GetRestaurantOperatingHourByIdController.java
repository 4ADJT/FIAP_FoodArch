package br.com.fiap.foodarch.application.controller.restaurants.operatingHour;

import java.util.UUID;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantOperatingHourPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours.GetRestaurantOperatingHourById;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/restaurants/operatingHours")
@Tag(name = "Restaurant - Operating hours")
public class GetRestaurantOperatingHourByIdController {
  private final GetRestaurantOperatingHourById getRestaurantById;

  public GetRestaurantOperatingHourByIdController(GetRestaurantOperatingHourById getRestaurantById) {
    this.getRestaurantById = getRestaurantById;
  }

  @GetMapping("/{restaurantId}")
  @Operation(summary = "Get restaurant operating hour by id", description = "Get restaurant operating hour by id from FoodArch.")
  public ResponseEntity<RestaurantOperatingHourOutput> getRestaurantAddressById(
      @ParameterObject
      @PathVariable UUID restaurantId
  ) {
    
    RestaurantOperatingHours address = getRestaurantById.execute(restaurantId);

    return ResponseEntity.ok(RestaurantOperatingHourPresenter.restaurantOperatingHourResponse(address));
  }
}
