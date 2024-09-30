package br.com.fiap.foodarch.application.controller.restaurants.tables;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantTablesPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesInput;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.tables.CreateRestaurantTables;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants/tables")
@Tag(name = "Tables")
public class CreateRestaurantTablesController {

    private final CreateRestaurantTables createRestaurantTables;

    public CreateRestaurantTablesController(CreateRestaurantTables createRestaurantTables) {
        this.createRestaurantTables = createRestaurantTables;
    }

    @PostMapping("/{ownerId}")
    @Operation(summary = "Create restaurant Table", description = "Create new restaunrat table to FoodArch")
    public ResponseEntity<RestaurantTablesOutput> createRestaurantTables(@RequestBody RestaurantTablesInput restaurantTablesInput, @PathVariable UUID ownerId) {
        RestaurantTables tables = createRestaurantTables.createTables(restaurantTablesInput, ownerId);
        return ResponseEntity.status(201).body(RestaurantTablesPresenter.response(tables));
    }


}
