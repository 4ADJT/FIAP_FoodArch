package br.com.fiap.foodarch.application.controller.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantInput;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.UpdateRestaurantName;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@Tag(name = "Restaurants")
public class UpdateRestaurantNameController {
  private final UpdateRestaurantName updateRestaurantName;

  public UpdateRestaurantNameController(UpdateRestaurantName updateRestaurantName) {
    this.updateRestaurantName = updateRestaurantName;
  }

  @PutMapping("/{ownerId}")
  @Operation(summary = "Update restaurant name", description = "Update restaurant name from FoodArch.")
  public ResponseEntity<RestaurantOutput> updateRestaurantName(
      @RequestParam("restaurantId") UUID restaurantId,
      @RequestBody RestaurantInput restaurantInput,
      @PathVariable UUID ownerId) {

    Restaurant updated = updateRestaurantName.execute(restaurantInput, ownerId, restaurantId);

    RestaurantOutput output = new RestaurantOutput(
        updated.getId(),
        updated.getName(),
        updated.getOwnerId(),
        updated.getCreatedAt()
    );

    return ResponseEntity.status(201).body(output);
  }

}
