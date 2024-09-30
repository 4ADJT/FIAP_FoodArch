package br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour.RestaurantOperatingHourRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.UpdateRestaurantOperatingHourFactory;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.NothingToUpdate;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.users.UserNotExistsException;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourInput;

public class UpdateRestaurantOperatingHour {
  private final RestaurantOperatingHourRepository repository;
  private final UserRepository userRepository;
  private final RestaurantRepository restaurantRepository;
  private final UpdateRestaurantOperatingHourFactory factory;

  public UpdateRestaurantOperatingHour(
      RestaurantOperatingHourRepository repository,
      UserRepository userRepository,
      RestaurantRepository restaurantRepository,
      UpdateRestaurantOperatingHourFactory factory) {
    this.repository = repository;
    this.userRepository = userRepository;
    this.restaurantRepository = restaurantRepository;
    this.factory = factory;
  }

  public RestaurantOperatingHours execute(
      UUID restaurantId,
      UUID ownerId,
      RestaurantOperatingHourInput restaurantOperatingHourInput
    ) {

    List<RestaurantOperatingHours> restaurantOperatingHours = repository.findByRestaurantId(restaurantId);

    RestaurantOperatingHours restaurantOperatingHourToUpdate = null;
    for (RestaurantOperatingHours restaurantOperatingHour : restaurantOperatingHours) {
      if (restaurantOperatingHour.getDayOfWeek() == restaurantOperatingHourInput.dayOfWeek()) {
        restaurantOperatingHourToUpdate = restaurantOperatingHour;
      }
    }

    if (restaurantOperatingHourToUpdate == null) {
      throw new NothingToUpdate("Nothing to update", HttpStatus.BAD_REQUEST);
    }
      
    User userTo = this.userRepository.findById(ownerId);

    if (userTo.getId() == null) {
      throw new UserNotExistsException("User not exists", HttpStatus.BAD_REQUEST);
    }

    Restaurant restaurant = this.restaurantRepository.findById(restaurantOperatingHourInput.restaurantId());

    if (restaurant.getId() == null) {
      throw new RestaurantNotFound("Restaurant not found", HttpStatus.BAD_REQUEST);
    }

    if (!restaurant.getOwnerId().equals(ownerId)) {
      throw new UserUnauthorizedException("User not authorized", HttpStatus.UNAUTHORIZED);
    }

    RestaurantOperatingHours reponseRestaurantOperatingHours = factory.updateRestaurantOperatingHour(
        restaurantOperatingHourToUpdate.getId(),
        restaurantOperatingHourToUpdate.getRestaurantId(),
        restaurantOperatingHourInput.dayOfWeek(),
        restaurantOperatingHourInput.openTime(),
        restaurantOperatingHourInput.closeTime(),
        restaurantOperatingHourToUpdate.getCreatedAt()
      );

    return repository.updateRestaurantOperatingHour(
        reponseRestaurantOperatingHours,
        restaurantOperatingHourInput.restaurantId());
  }
}
