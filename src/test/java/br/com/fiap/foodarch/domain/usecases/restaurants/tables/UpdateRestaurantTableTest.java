package br.com.fiap.foodarch.domain.usecases.restaurants.tables;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.restaurantTables.RestaurantTablesRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.UpdateRestaurantTableFactory;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.tables.RestaurantTablesInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateRestaurantTableTest {

    @Mock
    private RestaurantTablesRepository restaurantTablesRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private UpdateRestaurantTableFactory restaurantTableFactory;

    @InjectMocks
    private UpdateRestaurantTable updateRestaurantTable;

    private UUID restaurantId;
    private UUID ownerId;
    private RestaurantTablesInput restaurantTablesInput;
    private RestaurantTables restaurantTables;
    private User user;
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantId = UUID.randomUUID();
        ownerId = UUID.randomUUID();
        restaurantTablesInput = new RestaurantTablesInput(UUID.randomUUID(), restaurantId, 1, true);
        user = new User(ownerId, "John Doe", "john@example.com", LocalDate.of(1990, 1, 1), "12345678909", new HashSet<>(), LocalDateTime.now(), LocalDateTime.now());
        restaurant = new Restaurant(UUID.randomUUID(), "My Restaurant", ownerId, LocalDateTime.now(), LocalDateTime.now());
        restaurantTables = new RestaurantTables(UUID.randomUUID(), restaurantId, 1, true);
    }

    @Test
    public void testExecuteSuccessfully() {
        // Arrange
        when(restaurantTablesRepository.findByRestaurantId(restaurantTablesInput.id(), restaurantId)).thenReturn(restaurantTables);
        when(userRepository.findById(ownerId)).thenReturn(user);
        when(restaurantRepository.findById(restaurantId)).thenReturn(restaurant);
        when(restaurantTableFactory.updaateRestaurantTables(any(), any(), anyInt(), anyBoolean())).thenReturn(restaurantTables);
        when(restaurantTablesRepository.updateRestaurantTables(any(), any())).thenReturn(restaurantTables);

        // Act
        RestaurantTables result = updateRestaurantTable.execute(restaurantId, restaurantTablesInput, ownerId);

        // Assert
        assertNotNull(result);
        assertEquals(restaurantTablesInput.tableNumber(), result.getTableNumber());
        verify(restaurantTablesRepository).findByRestaurantId(restaurantTablesInput.id(), restaurantId);
        verify(userRepository).findById(ownerId);
        verify(restaurantRepository).findById(restaurantId);
        verify(restaurantTableFactory).updaateRestaurantTables(any(), any(), anyInt(), anyBoolean());
        verify(restaurantTablesRepository).updateRestaurantTables(any(), any());
    }

    @Test
    public void testExecuteUserUnauthorized() {
        // Arrange
        when(userRepository.findById(ownerId)).thenReturn(user);
        when(restaurantRepository.findById(restaurantTablesInput.restaurantId())).thenReturn(restaurant);
        restaurant.setOwnerId(UUID.randomUUID()); // Set a different ownerId

        // Act & Assert
        UserUnauthorizedException exception = assertThrows(UserUnauthorizedException.class, () -> {
            updateRestaurantTable.execute(restaurantId, restaurantTablesInput, ownerId);
        });
        assertEquals("User not authorized", exception.getMessage());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }
}
