package br.com.fiap.foodarch.application.controller.restaurants.operatingHour;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantOperatingHourPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours.GetRestaurantOperatingHourById;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<List<RestaurantOperatingHourOutput>> getRestaurantAddressById(
            @ParameterObject
            @PathVariable UUID restaurantId
    ) {

        List<RestaurantOperatingHours> restaurantOperationgHours = getRestaurantById.execute(restaurantId);
        List<RestaurantOperatingHourOutput> responses = new ArrayList<>();
        for (RestaurantOperatingHours restaurantOperatingHours : restaurantOperationgHours) {
            responses.add(RestaurantOperatingHourPresenter.restaurantOperatingHourResponse(restaurantOperatingHours));
        }

        return ResponseEntity.ok(responses);
    }
}
