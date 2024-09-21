package br.com.fiap.foodarch.application.controller.restaurants;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.GetRestaurantsByUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@Tag(name = "Restaurants")
public class GetRestaurantsByUserController {
  private final GetRestaurantsByUser getRestaurantsByUser;

  public GetRestaurantsByUserController(GetRestaurantsByUser getRestaurantsByUser) {
    this.getRestaurantsByUser = getRestaurantsByUser;
  }

  @GetMapping("/{ownerId}")
  @Operation(summary = "Get all restaurants by user", description = "Get all restaurants from FoodArch by user.")
  public ResponseEntity<Page<RestaurantOutput>> getRestaurantsByUser(
      @ParameterObject
      @PathVariable UUID ownerId,
      @PageableDefault(size = 10, page = 0) Pageable pageable
  ) {

    Page<Restaurant> list = getRestaurantsByUser.execute(ownerId, pageable);

    Page<RestaurantOutput> output = list.map(RestaurantPresenter::restaurantResponse);

    return ResponseEntity.ok(output);
  }
}
