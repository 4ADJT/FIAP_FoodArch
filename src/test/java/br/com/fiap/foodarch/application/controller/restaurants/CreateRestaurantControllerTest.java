package br.com.fiap.foodarch.application.controller.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantInput;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.CreateRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateRestaurantControllerTest {

    @Mock
    private CreateRestaurant createRestaurant;

    @InjectMocks
    private CreateRestaurantController createRestaurantController;

    private RestaurantInput restaurantInput;
    private UUID ownerId;
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerId = UUID.randomUUID();
        restaurantInput = new RestaurantInput("Pizza Place", ownerId);
        restaurant = new Restaurant(UUID.randomUUID(), "Pizza Place", ownerId, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testCreateRestaurantSuccessfully() {
        // Arrange
        when(createRestaurant.execute(any(RestaurantInput.class), eq(ownerId))).thenReturn(restaurant);

        // Act
        ResponseEntity<RestaurantOutput> response = createRestaurantController.createRestaurant(restaurantInput, ownerId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(restaurant.getName(), response.getBody().name());
        verify(createRestaurant).execute(restaurantInput, ownerId);
    }

    @Test
    public void testCreateRestaurantWithError() {
        // Arrange
        when(createRestaurant.execute(any(RestaurantInput.class), eq(ownerId))).thenThrow(new RuntimeException("Error creating restaurant"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            createRestaurantController.createRestaurant(restaurantInput, ownerId);
        });
        assertEquals("Error creating restaurant", exception.getMessage());
        verify(createRestaurant).execute(restaurantInput, ownerId);
    }
}
