package br.com.fiap.foodarch.domain.usecases.restaurants.kitchen;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.RestaurantKitchenRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class DeleteRestaurantKitchen {
    private UUID USERID;

    public UUID getUSERID() {
        return USERID;
    }

    public void setUSERID(UUID USERID) {
        this.USERID = USERID;
    }

    private final RestaurantKitchenRepository repository;
    private final RestaurantRepository restaurantRepository;

    public DeleteRestaurantKitchen(RestaurantRepository restaurantRepository, RestaurantKitchenRepository repository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    public void execute(UUID id, UUID ownerId) {

        Page<Restaurant> restaurant = this.restaurantRepository.findByOwnerId(ownerId, Pageable.unpaged());

        restaurant.map(rest -> {
            setUSERID(rest.getOwnerId());
            return rest.getOwnerId();
        });

        if (restaurant.isEmpty() || !getUSERID().equals(ownerId)) {
            throw new UserUnauthorizedException("Unauthorized action to user", HttpStatus.UNAUTHORIZED);
        }

        this.repository.delete(id);
    }

}
