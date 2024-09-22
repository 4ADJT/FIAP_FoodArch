package br.com.fiap.foodarch.application.controller.restaurants.address;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantAddressPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.address.GetRestaurantById;
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
@RequestMapping("/restaurants/address")
@Tag(name = "Restaurant - Address")
public class GetRestaurantAddressByIdController {
  private final GetRestaurantById getRestaurantById;

  public GetRestaurantAddressByIdController(GetRestaurantById getRestaurantById) {
    this.getRestaurantById = getRestaurantById;
  }

  @GetMapping("/{restaurantId}")
  @Operation(summary = "Get restaurant address by id", description = "Get restaurant address by id from FoodArch.")
  public ResponseEntity<RestaurantAddressOutput> getRestaurantAddressById(
      @ParameterObject
      @PathVariable UUID restaurantId
  ) {
    RestaurantAddresses address = getRestaurantById.execute(restaurantId);

    return ResponseEntity.ok(RestaurantAddressPresenter.restaurantAddressResponse(address));
  }
}
