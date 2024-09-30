package br.com.fiap.foodarch.application.controller.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.GetRestaurantsByUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetRestaurantsByUserControllerTest {

    @Mock
    private GetRestaurantsByUser getRestaurantsByUser;

    @InjectMocks
    private GetRestaurantsByUserController getRestaurantsByUserController;

    private Pageable pageable;
    private UUID ownerId;
    private Restaurant restaurant;
    private Page<Restaurant> restaurantPage;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pageable = Pageable.ofSize(10).withPage(0);
        ownerId = UUID.randomUUID();
        restaurant = new Restaurant(UUID.randomUUID(), "Burger Joint", ownerId, LocalDateTime.now(), LocalDateTime.now());
        restaurantPage = new PageImpl<>(Collections.singletonList(restaurant));
    }

    @Test
    public void testGetRestaurantsByUserSuccessfully() {
        // Arrange
        when(getRestaurantsByUser.execute(ownerId, pageable)).thenReturn(restaurantPage);

        // Act
        ResponseEntity<Page<RestaurantOutput>> response = getRestaurantsByUserController.getRestaurantsByUser(ownerId, pageable);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getContent().size());
        assertEquals(restaurant.getId(), response.getBody().getContent().get(0).id());
        verify(getRestaurantsByUser).execute(ownerId, pageable);
    }

    @Test
    public void testGetRestaurantsByUserEmptyList() {
        // Arrange
        when(getRestaurantsByUser.execute(ownerId, pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Act
        ResponseEntity<Page<RestaurantOutput>> response = getRestaurantsByUserController.getRestaurantsByUser(ownerId, pageable);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().isEmpty());
        verify(getRestaurantsByUser).execute(ownerId, pageable);
    }

    @Test
    public void testGetRestaurantsByUserWithError() {
        // Arrange
        when(getRestaurantsByUser.execute(ownerId, pageable)).thenThrow(new RuntimeException("Error fetching restaurants"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            getRestaurantsByUserController.getRestaurantsByUser(ownerId, pageable);
        });
        assertEquals("Error fetching restaurants", exception.getMessage());
        verify(getRestaurantsByUser).execute(ownerId, pageable);
    }
}
