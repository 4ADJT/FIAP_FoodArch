package br.com.fiap.foodarch.application.controller.restaurants;

import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.GetAllRestaurants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@Tag(name = "Restaurants")
public class GetAllRestaurantsController {
  private final GetAllRestaurants getAllRestaurants;

  public GetAllRestaurantsController(GetAllRestaurants getAllRestaurants) {
    this.getAllRestaurants = getAllRestaurants;
  }

  @GetMapping
  @Operation(summary = "Get all restaurants", description = "Get all restaurants from FoodArch.")
  public ResponseEntity<Page<RestaurantOutput>> getAllRestaurants(
      @ParameterObject
      @PageableDefault(size = 10, page = 0) Pageable pageable
  ) {

    Page<Restaurant> list = getAllRestaurants.execute(pageable);

    Page<RestaurantOutput> output = list.map(RestaurantPresenter::restaurantResponse);

    return ResponseEntity.ok(output);
  }

}
