package br.com.fiap.foodarch.domain.usecases.restaurants;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetRestaurantsByUserTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private GetRestaurantsByUser getRestaurantsByUser;

    private UUID ownerId;
    private Pageable pageable;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerId = UUID.randomUUID();
        pageable = Pageable.ofSize(10);
    }

    @Test
    public void testGetRestaurantsByUserSuccessfully() {
        // Arrange
        Restaurant restaurant1 = new Restaurant(UUID.randomUUID(), "Restaurant 1", ownerId, null, null);
        Restaurant restaurant2 = new Restaurant(UUID.randomUUID(), "Restaurant 2", ownerId, null, null);
        List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2);
        Page<Restaurant> restaurantPage = new PageImpl<>(restaurants, pageable, restaurants.size());

        when(restaurantRepository.findByOwnerId(ownerId, pageable)).thenReturn(restaurantPage);

        // Act
        Page<Restaurant> result = getRestaurantsByUser.execute(ownerId, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(ownerId, result.getContent().get(0).getOwnerId());
        assertEquals(ownerId, result.getContent().get(1).getOwnerId());
        verify(restaurantRepository).findByOwnerId(ownerId, pageable);
    }

    @Test
    public void testGetRestaurantsByUserEmpty() {
        // Arrange
        Page<Restaurant> emptyPage = new PageImpl<>(Arrays.asList(), pageable, 0);
        when(restaurantRepository.findByOwnerId(ownerId, pageable)).thenReturn(emptyPage);

        // Act
        Page<Restaurant> result = getRestaurantsByUser.execute(ownerId, pageable);

        // Assert
        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
        verify(restaurantRepository).findByOwnerId(ownerId, pageable);
    }
}
