package br.com.fiap.foodarch.domain.usecases.restaurants.tables;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.restaurantTables.RestaurantTablesRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.CreateRestaurantTablesFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesInput;
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

public class CreateRestaurantTablesTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantTablesRepository tablesRepository;

    @Mock
    private CreateRestaurantTablesFactory tablesFactory;

    @InjectMocks
    private CreateRestaurantTables createRestaurantTables;

    private UUID ownerId;
    private RestaurantTablesInput restaurantTablesInput;
    private User user;
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerId = UUID.randomUUID();
        restaurantTablesInput = new RestaurantTablesInput(UUID.randomUUID(), ownerId, 5, true);

        user = new User(ownerId, "John Doe", "john@example.com", null, "12345678901", null, null, null);
        restaurant = new Restaurant(restaurantTablesInput.restaurantId(), "Test Restaurant", ownerId, null, null);
    }

    @Test
    public void testCreateTablesSuccessfully() {
        // Arrange
        when(userRepository.findById(ownerId)).thenReturn(user);
        when(restaurantRepository.findById(restaurantTablesInput.restaurantId())).thenReturn(restaurant);

        RestaurantTables createdTable = new RestaurantTables(restaurantTablesInput.restaurantId(), restaurantTablesInput.tableNumber(), restaurantTablesInput.is_available());
        when(tablesFactory.createRestaurantTable(restaurantTablesInput.restaurantId(), restaurantTablesInput.tableNumber(), restaurantTablesInput.is_available())).thenReturn(createdTable);
        when(tablesRepository.createRestaurantTables(restaurantTablesInput.restaurantId(), createdTable)).thenReturn(createdTable);

        // Act
        RestaurantTables result = createRestaurantTables.createTables(restaurantTablesInput, ownerId);

        // Assert
        assertNotNull(result);
        assertEquals(restaurantTablesInput.tableNumber(), result.getTableNumber());
        assertEquals(restaurantTablesInput.is_available(), result.isAvailable());
        verify(userRepository).findById(ownerId);
        verify(restaurantRepository).findById(restaurantTablesInput.restaurantId());
        verify(tablesFactory).createRestaurantTable(restaurantTablesInput.restaurantId(), restaurantTablesInput.tableNumber(), restaurantTablesInput.is_available());
        verify(tablesRepository).createRestaurantTables(restaurantTablesInput.restaurantId(), createdTable);
    }

    @Test
    public void testCreateTablesUserUnauthorized() {
        // Arrange
        UUID differentOwnerId = UUID.randomUUID();
        restaurant.setOwnerId(differentOwnerId);
        when(userRepository.findById(ownerId)).thenReturn(user);
        when(restaurantRepository.findById(restaurantTablesInput.restaurantId())).thenReturn(restaurant);

        // Act & Assert
        UserUnauthorizedException exception = assertThrows(UserUnauthorizedException.class, () -> {
            createRestaurantTables.createTables(restaurantTablesInput, ownerId);
        });
        assertEquals("User not authorized", exception.getMessage());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
        verify(userRepository).findById(ownerId);
        verify(restaurantRepository).findById(restaurantTablesInput.restaurantId());
    }
}
