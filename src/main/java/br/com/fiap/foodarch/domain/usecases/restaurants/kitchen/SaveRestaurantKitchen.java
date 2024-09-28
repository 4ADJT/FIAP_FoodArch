package br.com.fiap.foodarch.domain.usecases.restaurants.kitchen;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.KitchenDefinitionRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.RestaurantKitchenRepository;
import br.com.fiap.foodarch.application.presenters.restaurants.RestaurantKitchenPresenter;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.CreateRestaurantKitchenFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.KitchensDefinition;
import br.com.fiap.foodarch.domain.entities.restaurants.kitchens.RestaurantKitchens;
import br.com.fiap.foodarch.domain.exceptions.restaurants.KitchenAlreadyExistsException;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.kitchen.RestaurantKitchenOutput;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

public class SaveRestaurantKitchen {

  private final RestaurantKitchenRepository repository;
  private final CreateRestaurantKitchenFactory factory;
  private final RestaurantRepository restaurantRepository;
  private final KitchenDefinitionRepository kitchenRepository;

  public SaveRestaurantKitchen(
      RestaurantKitchenRepository repository,
      RestaurantRepository restaurantRepository,
      CreateRestaurantKitchenFactory factory,
      KitchenDefinitionRepository kitchenRepository) {
    this.repository = repository;
    this.factory = factory;
    this.restaurantRepository = restaurantRepository;
    this.kitchenRepository = kitchenRepository;
  }

  public RestaurantKitchenOutput execute(UUID restaurantId,
                                         UUID kitchenId,
                                         UUID ownerId) {

    Optional<RestaurantKitchens> restaurantKitchen = this.repository.getByRestaurantIdAndKitchenId(
        restaurantId, kitchenId);

    Restaurant restaurant = this.restaurantRepository.findById(restaurantId);

    if(!restaurant.getOwnerId().equals(ownerId)) {
      throw new UserUnauthorizedException("Unauthorized action to user", HttpStatus.UNAUTHORIZED);
    }

    if(restaurantKitchen.isPresent()) {
      throw new KitchenAlreadyExistsException("Kitchen already exists in restaurantId: " +
          restaurantKitchen.get().getRestaurantId(), HttpStatus.BAD_REQUEST);
    }

    RestaurantKitchens savedEntity = this.repository.save(factory.createRestaurantKitchens(
        restaurantId, kitchenId
    ));

    KitchensDefinition kitchen = this.kitchenRepository.getById(kitchenId);

    return RestaurantKitchenPresenter.restaurantKitchenResponse(
        savedEntity, restaurant, kitchen
    );

  }

}
