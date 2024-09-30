package br.com.fiap.foodarch.domain.usecases.restaurants.tables;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.restaurantTables.RestaurantTablesRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.UpdateRestaurantTableFactory;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.users.UserNotExistsException;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesInput;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class UpdateRestaurantTable {

    private final RestaurantTablesRepository restaurantTablesRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final UpdateRestaurantTableFactory restaurantTableFactory;

    public UpdateRestaurantTable(RestaurantTablesRepository restaurantTablesRepository, UserRepository userRepository, RestaurantRepository restaurantRepository, UpdateRestaurantTableFactory restaurantTableFactory) {
        this.restaurantTablesRepository = restaurantTablesRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.restaurantTableFactory = restaurantTableFactory;
    }

    public RestaurantTables execute(UUID restaurantId, RestaurantTablesInput restaurantTablesInput, UUID ownerId) {

        RestaurantTables restaurantTablesUpdate = restaurantTablesRepository.findByRestaurantId(restaurantTablesInput.id(), restaurantId);

        User userTo = this.userRepository.findById(ownerId);

        if (userTo.getId() == null) {
            throw new UserNotExistsException("User not exists", HttpStatus.BAD_REQUEST);
        }

        Restaurant restaurant = this.restaurantRepository.findById(restaurantTablesInput.restaurantId());

        if (restaurant.getId() == null) {
            throw new RestaurantNotFound("Restaurant not found", HttpStatus.BAD_REQUEST);
        }

        if (!restaurant.getOwnerId().equals(ownerId)) {
            throw new UserUnauthorizedException("User not authorized", HttpStatus.UNAUTHORIZED);
        }

        RestaurantTables tables = restaurantTableFactory.updaateRestaurantTables(restaurantTablesUpdate.getId(), restaurantTablesUpdate.getRestaurantId(), restaurantTablesUpdate.getTableNumber(), restaurantTablesUpdate.isAvailable());

        return restaurantTablesRepository.updateRestaurantTables(tables, restaurantTablesInput.restaurantId());
    }
}
