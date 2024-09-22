package br.com.fiap.foodarch.application.controller.restaurants.address;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAddressPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressInput;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.address.CreateRestaurantAddress;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants/address")
@Tag(name = "Restaurant - Address")
public class CreateRestaurantAddressController {
  private final CreateRestaurantAddress createRestaurantAddress;

  public CreateRestaurantAddressController(CreateRestaurantAddress createRestaurantAddress) {
    this.createRestaurantAddress = createRestaurantAddress;
  }

  @PostMapping("/{restaurantId}")
  @Operation(summary = "Create restaurant address", description = "Create new restaurant address to FoodArch.")
  public ResponseEntity<RestaurantAddressOutput> createRestaurantAddress(@PathVariable UUID restaurantId,
                                                                         @RequestParam("ownerId") UUID ownerId,
                                                                         @RequestBody RestaurantAddressInput restaurantAddressInput) {
    RestaurantAddresses restaurantAddresses = createRestaurantAddress.execute(restaurantId, ownerId, restaurantAddressInput);

    return ResponseEntity.status(201).body(RestaurantAddressPresenter.restaurantAddressResponse(restaurantAddresses));
  }



}
