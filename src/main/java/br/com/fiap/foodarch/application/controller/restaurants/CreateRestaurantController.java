package br.com.fiap.foodarch.application.controller.restaurants;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantInput;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.CreateRestaurant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@Tag(name = "Restaurants")
public class CreateRestaurantController {
    private final CreateRestaurant createRestaurant;

    public CreateRestaurantController(CreateRestaurant createRestaurant) {
        this.createRestaurant = createRestaurant;
    }

    @PostMapping("/{ownerId}")
    @Operation(summary = "Create restaurant", description = "Create new restaurant to FoodArch.")
    public ResponseEntity<RestaurantOutput> createRestaurant(

            @RequestBody RestaurantInput restaurantInput, @PathVariable UUID ownerId) {
        Restaurant restaurant = createRestaurant.execute(restaurantInput, ownerId);

        return ResponseEntity.status(201).body(RestaurantPresenter.restaurantResponse(restaurant));
    }

}
