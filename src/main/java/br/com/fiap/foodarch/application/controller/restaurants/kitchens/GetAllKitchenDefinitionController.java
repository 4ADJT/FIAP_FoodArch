package br.com.fiap.foodarch.application.controller.restaurants.kitchens;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantKitchenDefinitionPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;
import br.com.fiap.foodarch.domain.records.restaurants.kitchen.KitchenDefinitionOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.kitchen.GetAllKitchenDefinitions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants/kitchens")
@Tag(name = "Restaurants - Kitchens Definitions")
public class GetAllKitchenDefinitionController {
    private final GetAllKitchenDefinitions getAllKitchenDefinitions;

    public GetAllKitchenDefinitionController(GetAllKitchenDefinitions getAllKitchenDefinitions) {
        this.getAllKitchenDefinitions = getAllKitchenDefinitions;
    }

    @GetMapping
    @Operation(summary = "Get all kitchens definitions")
    public ResponseEntity<List<KitchenDefinitionOutput>> getAllKitchenDefinitions() {

        List<KitchensDefinition> list = getAllKitchenDefinitions.execute();

        return ResponseEntity.ok(RestaurantKitchenDefinitionPresenter.restaurantKitchenDefinitionResponse(list));

    }
}
