package br.com.fiap.foodarch.application.controller.restaurants.tables;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantTablesPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesInput;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.tables.UpdateRestaurantTable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants/tables")
@Tag(name = "tables")
public class UpdateRestaurantTableController {

    private final UpdateRestaurantTable updateRestaurantTable;

    public UpdateRestaurantTableController(UpdateRestaurantTable updateRestaurantTable) {
        this.updateRestaurantTable = updateRestaurantTable;
    }

    @PutMapping("/{restaurantId}/{restaurantTableId}")
    @Operation(summary = "Update restaurant table", description = "Update a table for restaurant")
    public ResponseEntity<RestaurantTablesOutput> updateRestaurantTable(@PathVariable UUID restaurantId,@PathVariable UUID restaurantTableId, @RequestParam("ownerId") UUID ownerId, @RequestBody RestaurantTablesInput restaurantTablesInput) {
        RestaurantTables restaurantTables = updateRestaurantTable.execute(restaurantId,restaurantTableId, ownerId, restaurantTablesInput);
        return ResponseEntity.status(200).body(RestaurantTablesPresenter.response(restaurantTables));
    }

}
