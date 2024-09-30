package br.com.fiap.foodarch.domain.usecases.restaurants;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.UpdateRestaurantFactory;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateRestaurantNameTest {

    @Mock
    private RestaurantRepository repository;

    @Mock
    private UpdateRestaurantFactory factory;

    @InjectMocks
    private UpdateRestaurantName updateRestaurantName;

    private UUID ownerId;
    private UUID restaurantId;
    private Restaurant restaurant;
    private RestaurantInput restaurantInput;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerId = UUID.randomUUID();
        restaurantId = UUID.randomUUID();
        restaurantInput = new RestaurantInput("Updated Restaurant", ownerId);

        restaurant = new Restaurant(restaurantId, "Old Restaurant", ownerId, null, null);
    }

    @Test
    public void testUpdateRestaurantNameSuccessfully() {
        // Arrange
        when(repository.findById(restaurantId)).thenReturn(restaurant);

        Restaurant updatedRestaurant = new Restaurant(restaurantId, "Updated Restaurant", ownerId, restaurant.getCreatedAt(), null);
        when(factory.updateRestaurant(restaurantId, restaurantInput.name(), ownerId, restaurant.getCreatedAt())).thenReturn(updatedRestaurant);
        when(repository.updateRestaurant(updatedRestaurant, ownerId)).thenReturn(updatedRestaurant);

        // Act
        Restaurant result = updateRestaurantName.execute(restaurantInput, ownerId, restaurantId);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Restaurant", result.getName());
        assertEquals(ownerId, result.getOwnerId());
        verify(repository).findById(restaurantId);
        verify(factory).updateRestaurant(restaurantId, restaurantInput.name(), ownerId, restaurant.getCreatedAt());
        verify(repository).updateRestaurant(updatedRestaurant, ownerId);
    }

    @Test
    public void testUpdateRestaurantNameRestaurantNotFound() {
        // Arrange
        when(repository.findById(restaurantId)).thenReturn(null);

        // Act & Assert
        RestaurantNotFound exception = assertThrows(RestaurantNotFound.class, () -> {
            updateRestaurantName.execute(restaurantInput, ownerId, restaurantId);
        });
        assertEquals("Restaurant not found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        verify(repository).findById(restaurantId);
    }

    @Test
    public void testUpdateRestaurantNameUserUnauthorized() {
        // Arrange
        UUID differentOwnerId = UUID.randomUUID();
        restaurant.setOwnerId(differentOwnerId);
        when(repository.findById(restaurantId)).thenReturn(restaurant);

        // Act & Assert
        UserUnauthorizedException exception = assertThrows(UserUnauthorizedException.class, () -> {
            updateRestaurantName.execute(restaurantInput, ownerId, restaurantId);
        });
        assertEquals("You are not the owner of this restaurant", exception.getMessage());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
        verify(repository).findById(restaurantId);
    }
}
