package br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour.RestaurantOperatingHourRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantOperatingHourNotFoundException;
import jakarta.transaction.Transactional;

public class GetRestaurantOperatingHourById {
  private final RestaurantOperatingHourRepository repository;

  public GetRestaurantOperatingHourById(RestaurantOperatingHourRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public List<RestaurantOperatingHours> execute(UUID restaurantId) {
    if(restaurantId == null) {
      throw new IllegalArgumentException("Restaurant id is required");
    }

    if(repository.findByRestaurantId(restaurantId) == null) {
      throw new RestaurantOperatingHourNotFoundException("Restaurant operating hour not found", HttpStatus.NOT_FOUND);
    }

    return repository.findByRestaurantId(restaurantId);
  }
}
