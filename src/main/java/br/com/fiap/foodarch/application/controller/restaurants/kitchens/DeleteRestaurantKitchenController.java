package br.com.fiap.foodarch.application.controller.restaurants.kitchens;

import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;
import br.com.fiap.foodarch.domain.usecases.restaurants.kitchen.DeleteRestaurantKitchen;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@Tag(name = "Restaurants - Kitchens Definitions")
public class DeleteRestaurantKitchenController {
  private final DeleteRestaurantKitchen deleteRestaurantKitchen;

  public DeleteRestaurantKitchenController(DeleteRestaurantKitchen deleteRestaurantKitchen) {
    this.deleteRestaurantKitchen = deleteRestaurantKitchen;
  }

  @DeleteMapping("/kitchen/{id}")
  @Operation(summary = "Delete kitchen from restaurant", description = "Delete kitchen from restaurant from FoodArch.")
  public ResponseEntity<Void> deleteRestaurantKitchenController(
      @PathVariable UUID id,
      @RequestParam("ownerId") UUID ownerId
  ) {
    this.deleteRestaurantKitchen.execute(id, ownerId);

    return ResponseEntity.noContent().build();
  }

}
