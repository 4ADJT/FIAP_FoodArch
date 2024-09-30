package br.com.fiap.foodarch.application.controller.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.records.restaurants.RestaurantOutput;
import br.com.fiap.foodarch.domain.usecases.restaurants.GetAllRestaurants;
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
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllRestaurantsControllerTest {

    @Mock
    private GetAllRestaurants getAllRestaurants;

    @InjectMocks
    private GetAllRestaurantsController getAllRestaurantsController;

    private Pageable pageable;
    private List<Restaurant> restaurantList;
    private Page<Restaurant> restaurantPage;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pageable = Pageable.ofSize(10).withPage(0);
        restaurantList = Collections.singletonList(new Restaurant(UUID.randomUUID(), "Pizza Place", UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now()));
        restaurantPage = new PageImpl<>(restaurantList);
    }

    @Test
    public void testGetAllRestaurantsSuccessfully() {
        // Arrange
        when(getAllRestaurants.execute(pageable)).thenReturn(restaurantPage);

        // Act
        ResponseEntity<Page<RestaurantOutput>> response = getAllRestaurantsController.getAllRestaurants(pageable);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(restaurantList.size(), response.getBody().getContent().size());
        verify(getAllRestaurants).execute(pageable);
    }

    @Test
    public void testGetAllRestaurantsEmptyList() {
        // Arrange
        when(getAllRestaurants.execute(pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Act
        ResponseEntity<Page<RestaurantOutput>> response = getAllRestaurantsController.getAllRestaurants(pageable);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().isEmpty());
        verify(getAllRestaurants).execute(pageable);
    }

    @Test
    public void testGetAllRestaurantsWithError() {
        // Arrange
        when(getAllRestaurants.execute(pageable)).thenThrow(new RuntimeException("Error fetching restaurants"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            getAllRestaurantsController.getAllRestaurants(pageable);
        });
        assertEquals("Error fetching restaurants", exception.getMessage());
        verify(getAllRestaurants).execute(pageable);
    }
}
