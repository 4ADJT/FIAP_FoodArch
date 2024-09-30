package br.com.fiap.foodarch.domain.usecases.restaurants.kitchen;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.kitchen.RestaurantKitchenRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteRestaurantKitchenTest {

    @Mock
    private RestaurantKitchenRepository repository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private DeleteRestaurantKitchen deleteRestaurantKitchen;

    private UUID ownerId;
    private UUID kitchenId;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerId = UUID.randomUUID();
        kitchenId = UUID.randomUUID();
        restaurant = new Restaurant();
        restaurant.setOwnerId(ownerId);
    }

    @Test
    void execute_shouldThrowUserUnauthorizedException_whenRestaurantListIsEmpty() {
        // Given
        Page<Restaurant> restaurants = mock(Page.class);
        when(restaurants.isEmpty()).thenReturn(true);
        when(restaurantRepository.findByOwnerId(ownerId, Pageable.unpaged())).thenReturn(restaurants);

        // When / Then
        assertThrows(UserUnauthorizedException.class, () -> {
            deleteRestaurantKitchen.execute(kitchenId, ownerId);
        });
    }
}
