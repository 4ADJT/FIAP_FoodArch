package br.com.fiap.foodarch.domain.usecases.restaurants.address;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.address.RestaurantAddressRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.address.CreateRestaurantAddressFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantAddressAlreadyExistsException;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressInput;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class CreateRestaurantAddress {
    private final RestaurantAddressRepository repository;
    private final CreateRestaurantAddressFactory factory;
    private final RestaurantRepository restaurantRepository;

    public CreateRestaurantAddress(
            RestaurantAddressRepository repository,
            CreateRestaurantAddressFactory factory,
            RestaurantRepository restaurantRepository
    ) {
        this.repository = repository;
        this.factory = factory;
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantAddresses execute(UUID restaurantId,
                                       UUID ownerId,
                                       RestaurantAddressInput restaurantAddressInput) {
        Restaurant restaurant = this.restaurantRepository.findById(restaurantId);

        if (restaurant == null) {
            throw new RestaurantNotFound("Restaurant not found", HttpStatus.NOT_FOUND);
        }

        if (!restaurant.getOwnerId().equals(ownerId)) {
            throw new UserUnauthorizedException("User unauthorized", HttpStatus.UNAUTHORIZED);
        }

        if (repository.findByRestaurantId(restaurantId) != null) {
            throw new RestaurantAddressAlreadyExistsException("Restaurant address already exists", HttpStatus.BAD_REQUEST);
        }

        RestaurantAddresses restaurantAddress = factory.createRestaurantAddress(
                restaurant.getId(),
                restaurantAddressInput.street(),
                restaurantAddressInput.number(),
                restaurantAddressInput.complement(),
                restaurantAddressInput.neighborhood(),
                restaurantAddressInput.city(),
                restaurantAddressInput.state(),
                restaurantAddressInput.country(),
                restaurantAddressInput.zip_code()
        );

        return repository.createRestaurantAddress(restaurantAddress, restaurant.getId());
    }

}
