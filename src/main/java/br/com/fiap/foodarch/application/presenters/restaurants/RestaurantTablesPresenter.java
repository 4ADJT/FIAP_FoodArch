package br.com.fiap.foodarch.application.presenters.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesOutput;

public class RestaurantTablesPresenter {


    public static RestaurantTablesOutput response(RestaurantTables tables) {
        return new RestaurantTablesOutput(
                tables.getRestaurantId(),
                tables.getId(),
                tables.getTableNumber(),
                tables.isAvailable(),
                tables.getCreatedAt(),
                tables.getUpdatedAt()
        );
    }
}
