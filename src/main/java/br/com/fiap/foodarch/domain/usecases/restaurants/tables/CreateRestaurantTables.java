package br.com.fiap.foodarch.domain.usecases.restaurants.tables;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.restaurantTables.RestaurantTablesRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.CreateRestaurantTablesFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.users.UserNotExistsException;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesInput;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class CreateRestaurantTables {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantTablesRepository tablesRepository;
    private final CreateRestaurantTablesFactory tablesFactory;


    public CreateRestaurantTables(UserRepository userRepository, RestaurantRepository restaurantRepository, RestaurantTablesRepository tablesRepository, CreateRestaurantTablesFactory tablesFactory) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.tablesRepository = tablesRepository;
        this.tablesFactory = tablesFactory;
    }

    public RestaurantTables createTables(RestaurantTablesInput restaurantTablesInput, UUID ownerId) {

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

        RestaurantTables tables = tablesFactory.createRestaurantTable(restaurantTablesInput.restaurantId(), restaurantTablesInput.tableNumber(), restaurantTablesInput.is_available());

        return tablesRepository.createRestaurantTables(restaurantTablesInput.restaurantId(), tables);

    }

}
