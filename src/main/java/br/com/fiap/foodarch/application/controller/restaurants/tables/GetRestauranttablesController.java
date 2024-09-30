package br.com.fiap.foodarch.application.controller.restaurants.tables;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantTablesPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.tables.GetRestaurantTablesById;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants/tables")
@Tag(name = "tables")
public class GetRestauranttablesController {

    private final GetRestaurantTablesById getRestaurantTablesById;

    public GetRestauranttablesController(GetRestaurantTablesById getRestaurantTablesById) {
        this.getRestaurantTablesById = getRestaurantTablesById;
    }

    @GetMapping("/{tableId}")
    @Operation(summary = "Get restaurant table by id", description = "Get restaurant table by id from FoodArch.")
    public ResponseEntity<RestaurantTablesOutput> getRestaurantTableById(@ParameterObject @PathVariable int tableNumber) {
        RestaurantTables tables = getRestaurantTablesById.execute(tableNumber);

        return ResponseEntity.ok(RestaurantTablesPresenter.response(tables));

    }
}
