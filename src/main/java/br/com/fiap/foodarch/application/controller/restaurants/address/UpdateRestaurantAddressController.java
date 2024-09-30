package br.com.fiap.foodarch.application.controller.restaurants.address;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAddressPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressInput;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.address.UpdateRestaurantAddress;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants/address")
@Tag(name = "Restaurant - Address")
public class UpdateRestaurantAddressController {
  private final UpdateRestaurantAddress updateRestaurantAddress;

  public UpdateRestaurantAddressController(UpdateRestaurantAddress updateRestaurantAddress) {
    this.updateRestaurantAddress = updateRestaurantAddress;
  }

  @PutMapping("/{restaurantId}")
  @Operation(summary = "Update restaurant address", description = "Update restaurant address")
  public ResponseEntity<RestaurantAddressOutput> updateRestaurantAddress(
      @PathVariable UUID restaurantId,
      @RequestParam("ownerId") UUID ownerId,
      @RequestBody RestaurantAddressInput restaurantAddressInput
  ) {
    RestaurantAddresses restaurantAddress = updateRestaurantAddress.execute(restaurantId, ownerId, restaurantAddressInput);

    return ResponseEntity.status(201).body(RestaurantAddressPresenter.restaurantAddressResponse(restaurantAddress));
  }

}
