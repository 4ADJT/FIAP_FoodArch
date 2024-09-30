package br.com.fiap.foodarch.application.controller.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantInput;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.UpdateRestaurantName;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateRestaurantNameControllerTest {

    @Mock
    private UpdateRestaurantName updateRestaurantName;

    @InjectMocks
    private UpdateRestaurantNameController updateRestaurantNameController;

    private UUID ownerId;
    private UUID restaurantId;
    private RestaurantInput restaurantInput;
    private Restaurant updatedRestaurant;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerId = UUID.randomUUID();
        restaurantId = UUID.randomUUID();
        restaurantInput = new RestaurantInput("New Restaurant Name", ownerId);
        updatedRestaurant = new Restaurant(restaurantId, "New Restaurant Name", ownerId, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testUpdateRestaurantNameSuccessfully() {
        // Arrange
        when(updateRestaurantName.execute(restaurantInput, ownerId, restaurantId)).thenReturn(updatedRestaurant);

        // Act
        ResponseEntity<RestaurantOutput> response = updateRestaurantNameController.updateRestaurantName(restaurantId, restaurantInput, ownerId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedRestaurant.getId(), response.getBody().id());
        assertEquals(updatedRestaurant.getName(), response.getBody().name());
        verify(updateRestaurantName).execute(restaurantInput, ownerId, restaurantId);
    }

    @Test
    public void testUpdateRestaurantNameUserNotAuthorized() {
        // Arrange
        when(updateRestaurantName.execute(restaurantInput, ownerId, restaurantId)).thenThrow(new UserUnauthorizedException("User not authorized", HttpStatus.UNAUTHORIZED));

        // Act & Assert
        UserUnauthorizedException exception = assertThrows(UserUnauthorizedException.class, () -> {
            updateRestaurantNameController.updateRestaurantName(restaurantId, restaurantInput, ownerId);
        });
        assertEquals("User not authorized", exception.getMessage());
        verify(updateRestaurantName).execute(restaurantInput, ownerId, restaurantId);
    }

    @Test
    public void testUpdateRestaurantNameRestaurantNotFound() {
        // Arrange
        when(updateRestaurantName.execute(restaurantInput, ownerId, restaurantId)).thenThrow(new RestaurantNotFound("Restaurant not found", HttpStatus.NOT_FOUND));

        // Act & Assert
        RestaurantNotFound exception = assertThrows(RestaurantNotFound.class, () -> {
            updateRestaurantNameController.updateRestaurantName(restaurantId, restaurantInput, ownerId);
        });
        assertEquals("Restaurant not found", exception.getMessage());
        verify(updateRestaurantName).execute(restaurantInput, ownerId, restaurantId);
    }
}
