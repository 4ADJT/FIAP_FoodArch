package br.com.fiap.foodarch.domain.usecases.restaurants.tables;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.restaurantTables.RestaurantTablesRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantTablesNotFoundException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class GetRestaurantTablesById {
    private final RestaurantTablesRepository restaurantTablesRepository;

    public GetRestaurantTablesById(RestaurantTablesRepository restaurantTablesRepository) {
        this.restaurantTablesRepository = restaurantTablesRepository;
    }

    public RestaurantTables execute(UUID tableNumber) {
        if (tableNumber == null) {
            throw new IllegalArgumentException("Table number cannot be null");
        }

        RestaurantTables tables = restaurantTablesRepository.findByRestaurantTableNumber(tableNumber);

        if (tables == null) {
            throw new RestaurantTablesNotFoundException("Restaurant table number not found", HttpStatus.NOT_FOUND);
        }

        return tables;
    }
}
