package br.com.fiap.foodarch.application.controller.restaurants.kitchens;

import br.com.fiap.foodarch.domain.records.restaurants.kitchen.RestaurantKitchenOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.kitchen.SaveRestaurantKitchen;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@Tag(name = "Restaurants - Kitchens Definitions")
public class SaveKitchenInRestaurantController {
    private final SaveRestaurantKitchen saveRestaurantKitchen;

    public SaveKitchenInRestaurantController(SaveRestaurantKitchen saveRestaurantKitchen) {
        this.saveRestaurantKitchen = saveRestaurantKitchen;
    }

    @PostMapping("/kitchen/{restaurantId}/{kitchenId}")
    @Operation(summary = "Save kitchen to restaurant", description = "Save kitchen to restaurant from FoodArch.")
    public ResponseEntity<RestaurantKitchenOutput> saveKitchenInRestaurantController(
            @PathVariable UUID restaurantId,
            @PathVariable UUID kitchenId,
            @RequestParam("ownerId") UUID ownerId
    ) {
        RestaurantKitchenOutput restaurantKitchenOutput = this.saveRestaurantKitchen.execute(restaurantId, kitchenId, ownerId);

        return ResponseEntity.status(201).body(restaurantKitchenOutput);

    }
}
