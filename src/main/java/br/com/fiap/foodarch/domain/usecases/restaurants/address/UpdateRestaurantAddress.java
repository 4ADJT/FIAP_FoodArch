package br.com.fiap.foodarch.domain.usecases.restaurants.address;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.address.RestaurantAddressRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.address.RestaurantAddresses;
import br.com.fiap.foodarch.domain.entities.restaurants.address.UpdateRestaurantAddressFactory;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.address.RestaurantAddressInput;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class UpdateRestaurantAddress {
    private final RestaurantAddressRepository repository;
    private final UpdateRestaurantAddressFactory factory;
    private final RestaurantRepository restaurantRepository;

    public UpdateRestaurantAddress(
            RestaurantAddressRepository repository,
            UpdateRestaurantAddressFactory factory,
            RestaurantRepository restaurantRepository
    ) {
        this.repository = repository;
        this.factory = factory;
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantAddresses execute(
            UUID restaurantId,
            UUID ownerId,
            RestaurantAddressInput restaurantAddressInput
    ) {

        RestaurantAddresses restaurantAddressToUpdate = repository.findByRestaurantId(restaurantId);

        if (restaurantAddressToUpdate == null) {
            throw new RestaurantNotFound("Restaurant address not found. Create before update",
                    HttpStatus.NOT_FOUND);
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantAddressToUpdate.getRestaurantId());

        if (restaurant == null) {
            throw new RestaurantNotFound("Restaurant not found.", HttpStatus.NOT_FOUND);
        }

        if (!restaurant.getOwnerId().equals(ownerId)) {
            throw new UserUnauthorizedException("User unauthorized", HttpStatus.UNAUTHORIZED);
        }

        if (!restaurantAddressInput.restaurantId().equals(restaurantId)) {
            throw new RestaurantNotFound("The restaurant ID for reference is not the same. " +
                    "Check which restaurant should be updated.", HttpStatus.BAD_REQUEST);
        }

        RestaurantAddresses restaurantAddress = factory.updateRestaurantAddress(
                restaurantAddressToUpdate.getId(),
                restaurantAddressToUpdate.getRestaurantId(),
                restaurantAddressInput.street(),
                restaurantAddressInput.number(),
                restaurantAddressInput.complement(),
                restaurantAddressInput.neighborhood(),
                restaurantAddressInput.city(),
                restaurantAddressInput.state(),
                restaurantAddressInput.country(),
                restaurantAddressInput.zip_code(),
                restaurantAddressToUpdate.getCreatedAt()
        );

        return repository.updateRestaurantAddress(restaurantAddress, restaurantAddressToUpdate.getRestaurantId());
    }

}
